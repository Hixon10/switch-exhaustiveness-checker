package ru.hixon.switchexhaustivenesschecker;

import com.sun.source.tree.*;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.lang.model.element.ElementKind.ENUM_CONSTANT;

final class TestMethodTreePathScanner extends TreePathScanner<Void, Void> {
    private final Trees trees;
    private final CompilationUnitTree compilationUnitTree;

    private final Messager messager;
    private final Set<MethodTree> methodsForAnalysis;
    private final Name classForAnalysis;

    public TestMethodTreePathScanner(
            final Trees trees,
            final CompilationUnitTree compilationUnitTree,
            final Messager messager,
            final Set<MethodTree> methodsForAnalysis,
            final Name classForAnalysis) {
        this.trees = trees;
        this.compilationUnitTree = compilationUnitTree;
        this.messager = messager;
        this.methodsForAnalysis = methodsForAnalysis;
        this.classForAnalysis = classForAnalysis;
    }

    @Override
    public Void visitMethod(final MethodTree methodTree, final Void unused) {
        if (isAnalyseOnlySpecificMethodsInClass() &&
                !methodsForAnalysis.remove(methodTree)) {
            // if we want to analyse only some methods in a class,
            // and current method is not part of analysis set,
            // we just need to ignore it
            return null;
        }

        for (final StatementTree statementTree : methodTree.getBody().getStatements()) {
            if (statementTree.getKind() != Tree.Kind.SWITCH) {
                continue;
            }
            processCurrentSwitchStatement((SwitchTree) statementTree, methodTree.getName());
        }

        return null;
    }

    private void processCurrentSwitchStatement(SwitchTree statementTree, Name currentMethodName) {
        final ExpressionTree switchTreeExpression = statementTree.getExpression();

        final TreePath treePath = TreePath.getPath(compilationUnitTree, switchTreeExpression);
        final TypeMirror typeMirror = trees.getTypeMirror(treePath);

        if (typeMirror.getKind() != TypeKind.DECLARED) {
            return;
        }

        Element enumElement = ((DeclaredType) typeMirror).asElement();
        if (enumElement.getKind() != ElementKind.ENUM) {
            return;
        }

        List<Element> enumValues = getEnumValuesForGivenEnum(enumElement);
        int numberOfNonDefaultSwitchCases = getNumberOfNonDefaultSwitchCases(statementTree);

        if (numberOfNonDefaultSwitchCases != enumValues.size()) {
            String methodNameAsString = currentMethodName.toString();
            if (methodNameAsString.equals("<init>")) {
                // fix constructor name
                methodNameAsString = classForAnalysis + "()";
            }
            messager.printMessage(Diagnostic.Kind.ERROR, "Some switch branches in class: " + classForAnalysis + ", method: " + methodNameAsString + " are not covered", trees.getElement(treePath));
        }
    }

    private int getNumberOfNonDefaultSwitchCases(SwitchTree switchTree) {
        int count = 0;
        for (CaseTree caseTree : switchTree.getCases()) {
            // we don't want to count default statement
            if (caseTree.getExpression() != null) {
                count++;
            }
        }
        return count;
    }

    private List<Element> getEnumValuesForGivenEnum(Element enumElement) {
        List<Element> enumConstants = new ArrayList<>();
        for (Element element : enumElement.getEnclosedElements()) {
            if (element.getKind() == ENUM_CONSTANT) {
                enumConstants.add(element);
            }
        }
        return enumConstants;
    }

    private boolean isAnalyseOnlySpecificMethodsInClass() {
        return methodsForAnalysis != null;
    }
}

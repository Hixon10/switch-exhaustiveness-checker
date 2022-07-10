package ru.hixon.switchexhaustivenesschecker;

import com.sun.source.tree.*;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.*;
import java.util.stream.Collectors;

import static javax.lang.model.element.ElementKind.ENUM_CONSTANT;

final class TestMethodTreePathScanner extends TreePathScanner<Void, Void> {
    private final Trees trees;
    private final CompilationUnitTree compilationUnitTree;

    private final Messager messager;
    private final Set<MethodTree> methodsForAnalysis;
    private final Name classForAnalysis;
    private final Map<Name, TypeElement> classesForAnalysis;

    TestMethodTreePathScanner(
            final Trees trees,
            final CompilationUnitTree compilationUnitTree,
            final Messager messager,
            final Set<MethodTree> methodsForAnalysis,
            final Name classForAnalysis,
            final Map<Name, TypeElement> classesForAnalysis) {
        this.trees = trees;
        this.compilationUnitTree = compilationUnitTree;
        this.messager = messager;
        this.methodsForAnalysis = methodsForAnalysis;
        this.classForAnalysis = classForAnalysis;
        this.classesForAnalysis = classesForAnalysis;
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

        if (!canProcessThisClass(methodTree)) {
            // for inner classes we receive event from parent class.
            // if we have several inner classes inside parent class,
            // we don't want to process all inner classes,
            // if only some of them are annotated with SwitchExhaustive
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

    private boolean canProcessThisClass(MethodTree methodTree) {
        TreePath treePath = TreePath.getPath(compilationUnitTree, methodTree);
        String parentClassNameForMethod = trees.getElement(treePath).getEnclosingElement().toString();
        // parentClassNameForMethod A.B
        // A - yes
        // A.B.C - no
        boolean continueProcessing = false;
        for (Name className : classesForAnalysis.keySet()) {
            String cls = className.toString();
            if (parentClassNameForMethod.equals(cls) || parentClassNameForMethod.startsWith(cls + '.')) {
                continueProcessing = true;
            }
        }
        return continueProcessing;
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

        Set<String> enumValues = getEnumValuesForGivenEnum(enumElement);
        Set<String> nonDefaultCoveredEnums = getNonDefaultCoveredSwitchCases(statementTree);

        if (nonDefaultCoveredEnums.size() != enumValues.size()) {
            String methodOrConstructorStr = "method";
            String methodOrConstructorNameAsString = currentMethodName.toString();
            if (methodOrConstructorNameAsString.equals("<init>")) {
                // fix constructor name
                methodOrConstructorStr = "constructor";
                methodOrConstructorNameAsString = classForAnalysis + "()";
            }

            String nonCoveredEnumsAsStr = enumValues.stream()
                    .filter(e -> !nonDefaultCoveredEnums.contains(e))
                    .collect(Collectors.joining(", "));

            messager.printMessage(Diagnostic.Kind.ERROR, "Switch branches: [" + nonCoveredEnumsAsStr + "] in class: [" + classForAnalysis + "], " + methodOrConstructorStr + ": [" + methodOrConstructorNameAsString + "] are not covered", trees.getElement(treePath));
        }
    }

    private Set<String> getNonDefaultCoveredSwitchCases(SwitchTree switchTree) {
        Set<String> coveredEnums = new HashSet<>();
        for (CaseTree caseTree : switchTree.getCases()) {
            // we don't want to count default statement
            if (caseTree.getExpression() != null) {
                coveredEnums.add(caseTree.getExpression().toString());
            }
        }
        return coveredEnums;
    }

    private Set<String> getEnumValuesForGivenEnum(Element enumElement) {
        Set<String> enumConstants = new HashSet<>();
        for (Element element : enumElement.getEnclosedElements()) {
            if (element.getKind() == ENUM_CONSTANT) {
                enumConstants.add(element.getSimpleName().toString());
            }
        }
        return enumConstants;
    }

    private boolean isAnalyseOnlySpecificMethodsInClass() {
        return methodsForAnalysis != null;
    }
}

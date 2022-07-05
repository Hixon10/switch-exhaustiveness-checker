package ru.hixon.switchexhaustivenesschecker;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import com.sun.source.tree.*;
import com.sun.source.util.*;

public class SwitchExhaustiveCheckerProcessor extends AbstractProcessor {
    private final AnalyzeTaskListener analyzeTaskListener = new AnalyzeTaskListener(this);
    private final Map<Name, Boolean> remainingTypeElementNames = new HashMap<>();
    private final Map<Name, Set<MethodTree>> annotatedMethodsInClasses = new HashMap<>();

    private Messager messager;
    private Trees trees;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        this.messager = processingEnv.getMessager();
        this.trees = Trees.instance(processingEnv);

        JavacTask.instance(processingEnv).addTaskListener(analyzeTaskListener);
    }

    public void handleAnalyzedType(final TypeElement typeElement) {
        Boolean needProcessAllMethodsInClass = remainingTypeElementNames.remove(typeElement.getQualifiedName());
        if (needProcessAllMethodsInClass == null) {
            // we don't know this class, so we don't need to process it
            return;
        }

        final TreePath treePath = trees.getPath(typeElement);

        Set<MethodTree> methodsForAnalysis = annotatedMethodsInClasses.remove(typeElement.getQualifiedName());
        if (needProcessAllMethodsInClass) {
            // null means that we need to process all methods
            methodsForAnalysis = null;
        }

        processType(trees, typeElement, treePath, methodsForAnalysis);
    }

    private void processType(Trees trees, TypeElement typeElement, TreePath treePath, Set<MethodTree> methodsForAnalysis) {
        final CompilationUnitTree compilationUnitTree = treePath.getCompilationUnit();
        final TestMethodTreePathScanner treePathScanner = new TestMethodTreePathScanner(trees, compilationUnitTree, messager, methodsForAnalysis, typeElement.getQualifiedName());
        treePathScanner.scan(compilationUnitTree, null);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(SwitchExhaustive.class);

        for (Element annotatedElement : elementsAnnotatedWith) {
            try {
                switch (annotatedElement.getKind()) {
                    case METHOD:
                    case CONSTRUCTOR:
                        TypeElement declaringClass = (TypeElement) annotatedElement.getEnclosingElement();
                        Name className = declaringClass.getQualifiedName();
                        remainingTypeElementNames.putIfAbsent(className, false);

                        annotatedMethodsInClasses.computeIfAbsent(className, ignored -> new HashSet<>())
                                        .add((MethodTree) trees.getTree(annotatedElement));
                        break;
                    case CLASS:
                        Name currentClassName = ElementFilter.typesIn(Collections.singletonList(annotatedElement)).get(0).getQualifiedName();
                        remainingTypeElementNames.put(currentClassName, true);
                        break;
                }
            } catch (Exception e) {
                error(annotatedElement, "got an error while process(): " + getExceptionStackTrace(e) + " " + e.getMessage());
                throw new RuntimeException("got an error while process()", e);
            }
        }

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(SwitchExhaustive.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(Element element, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), element);
    }

    private static String getExceptionStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}

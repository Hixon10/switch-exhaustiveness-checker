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

/**
 * An annotation processor, which checks that
 * every class and method, which are annotated by {@link SwitchExhaustive},
 * covers all Enum constants in switch statement cases.
 */
public class SwitchExhaustiveCheckerProcessor extends AbstractProcessor {
    private final AnalyzeTaskListener analyzeTaskListener = new AnalyzeTaskListener(this);
    private final Map<Name, Boolean> remainingTypeElementNames = new HashMap<>();
    private final Map<Name, Set<MethodTree>> annotatedMethodsInClasses = new HashMap<>();

    private final Map<Name, TypeElement> nameToTypeElement = new HashMap<>();

    private Messager messager;
    private Trees trees;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        this.messager = processingEnv.getMessager();
        this.trees = Trees.instance(processingEnv);

        JavacTask.instance(processingEnv).addTaskListener(analyzeTaskListener);
    }

    void handleAnalyzedType(final TypeElement eventTypeElement, final CompilationUnitTree compilationUnit) {
        Name eventClassName = eventTypeElement.getQualifiedName();
        Boolean needProcessAllMethodsInClass = remainingTypeElementNames.remove(eventClassName);
        TypeElement typeElement = eventTypeElement;

        if (needProcessAllMethodsInClass == null) {
            // maybe it is internal class, let's try to find it
            Map.Entry<Name, Boolean> entry = findNeedProcessAllMethodsInClassForNestedClass(eventClassName.toString());
            if (entry == null) {
                // we don't know this class, so we don't need to process it
                return;
            }

            needProcessAllMethodsInClass = entry.getValue();
            eventClassName = entry.getKey();

            // we want to get child type, instead of parent
            typeElement = nameToTypeElement.getOrDefault(eventClassName, eventTypeElement);
        }

        final TreePath treePath = trees.getPath(typeElement);

        Set<MethodTree> methodsForAnalysis = annotatedMethodsInClasses.remove(eventClassName);
        if (needProcessAllMethodsInClass) {
            // null means that we need to process all methods
            methodsForAnalysis = null;
        }

        processType(trees, typeElement, treePath, methodsForAnalysis, compilationUnit);
    }

    /**
     * map {@link this#remainingTypeElementNames} contains "a.b.C.D", and we got event about "a.b.C",
     * we want to fallback to "a.b.C.D" in this case
     *
     * O(N) implementation, but who cares.
     */
    private Map.Entry<Name, Boolean> findNeedProcessAllMethodsInClassForNestedClass(final String prefixClassName) {
        Iterator<Map.Entry<Name, Boolean>> iter = remainingTypeElementNames.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Name, Boolean> entry = iter.next();
            final String fullClassName = entry.getKey().toString();
            if (fullClassName.startsWith(prefixClassName)) {
                final String suffixClassName = fullClassName.substring(prefixClassName.length());
                if (!suffixClassName.isEmpty() && suffixClassName.charAt(0) == '.') {
                    boolean found = true;
                    for (int i = 1; i < suffixClassName.length(); i++) {
                        // check that suffixClassName is valid className
                        // we want to be sure, that it is inner class with exactly one level of nesting
                        if (!Character.isJavaIdentifierPart(suffixClassName.charAt(i))) {
                            found = false;
                            break;
                        }
                    }

                    if (found) {
                        iter.remove();
                        return entry;
                    }
                }
            }
        }

        return null;
    }

    private void processType(Trees trees, TypeElement typeElement, TreePath treePath, Set<MethodTree> methodsForAnalysis, CompilationUnitTree compilationUnit) {
        final CompilationUnitTree compilationUnitTree;
        if (treePath == null) {
            // fallback for inner classes
            compilationUnitTree = compilationUnit;
        } else {
            compilationUnitTree = treePath.getCompilationUnit();
        }

        final TestMethodTreePathScanner treePathScanner = new TestMethodTreePathScanner(trees, compilationUnitTree, messager, methodsForAnalysis, typeElement.getQualifiedName(), nameToTypeElement);
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
                        nameToTypeElement.putIfAbsent(className, declaringClass);

                        annotatedMethodsInClasses.computeIfAbsent(className, ignored -> new HashSet<>())
                                        .add((MethodTree) trees.getTree(annotatedElement));
                        break;
                    case CLASS:
                        TypeElement currenTypeElement = ElementFilter.typesIn(Collections.singletonList(annotatedElement)).get(0);
                        Name currentClassName = currenTypeElement.getQualifiedName();
                        remainingTypeElementNames.put(currentClassName, true);
                        nameToTypeElement.putIfAbsent(currentClassName, currenTypeElement);
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

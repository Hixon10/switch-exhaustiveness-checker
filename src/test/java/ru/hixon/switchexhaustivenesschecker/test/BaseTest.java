package ru.hixon.switchexhaustivenesschecker.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import ru.hixon.switchexhaustivenesschecker.SwitchExhaustiveCheckerProcessor;
import ru.hixon.switchexhaustivenesschecker.util.JavaSourceFromString;

import javax.tools.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class BaseTest {

    @Rule
    public final TemporaryFolder tempDir = new TemporaryFolder();

    protected void withMissedBranchesBase(String className, String classPath, String errorMessage) throws IOException {
        CompilationResult compilationResult = compileClass(className, classPath);
        String diagnosticsAsString = compilationResult.getDiagnosticsAsString();
        Assert.assertFalse(diagnosticsAsString, compilationResult.isOk());

        Assert.assertTrue(diagnosticsAsString, diagnosticsAsString.contains(errorMessage));

        // check without annotation processor
        compilationResult = compileClass(className, classPath, true);
        Assert.assertTrue(compilationResult.getDiagnosticsAsString(), compilationResult.isOk());
    }

    protected void withoutMissedBranchesTest(String className, String classPath) throws IOException {
        CompilationResult compilationResult = compileClass(className, classPath);
        Assert.assertTrue(compilationResult.getDiagnosticsAsString(), compilationResult.isOk());

        // check without annotation processor
        compilationResult = compileClass(className, classPath, true);
        Assert.assertTrue(compilationResult.getDiagnosticsAsString(), compilationResult.isOk());
    }

    protected CompilationResult compileClass(String className, String classPath) throws IOException {
        return compileClass(className, classPath, false);
    }

    protected CompilationResult compileClass(String className, String classPath, boolean removeSwitchExhaustiveAnnotation) throws IOException {
        String classLines = getResourceFileAsString(classPath);

        if (removeSwitchExhaustiveAnnotation) {
            classLines = classLines.replace("@SwitchExhaustive", "");
        }

        JavaFileObject file = new JavaSourceFromString(className, classLines);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(tempDir.getRoot()));

        Iterable<? extends JavaFileObject> compilationUnits = Collections.singletonList(file);
        JavaCompiler.CompilationTask compilerTask = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
        compilerTask.setProcessors(Collections.singletonList(new SwitchExhaustiveCheckerProcessor()));
        boolean ok = compilerTask.call();

        return new CompilationResult(ok, diagnostics.getDiagnostics());
    }

    /**
     * Reads given resource file as a string.
     * https://stackoverflow.com/a/46613809/1756750
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    protected String getResourceFileAsString(String fileName) throws IOException {
        try (InputStream is = getClass().getResource(fileName).openStream()) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}

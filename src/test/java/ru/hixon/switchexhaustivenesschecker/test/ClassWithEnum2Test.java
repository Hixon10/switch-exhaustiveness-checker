package ru.hixon.switchexhaustivenesschecker.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class ClassWithEnum2Test extends BaseTest {

    @Test
    public void constructorWithMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum2ConstructorBad";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorBad.java";

        CompilationResult compilationResult = compileClass(className, classPath);
        String diagnosticsAsString = compilationResult.getDiagnosticsAsString();
        Assert.assertFalse(diagnosticsAsString, compilationResult.isOk());
        Assert.assertTrue(diagnosticsAsString, diagnosticsAsString.contains("error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad, method: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad() are not covered"));

        // check without annotation processor
        compilationResult = compileClass(className, classPath, true);
        Assert.assertTrue(compilationResult.getDiagnosticsAsString(), compilationResult.isOk());
    }

    @Test
    public void constructorWithoutMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum2ConstructorGood";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorGood.java";

        CompilationResult compilationResult = compileClass(className, classPath);
        Assert.assertTrue(compilationResult.getDiagnosticsAsString(), compilationResult.isOk());

        // check without annotation processor
        compilationResult = compileClass(className, classPath, true);
        Assert.assertTrue(compilationResult.getDiagnosticsAsString(), compilationResult.isOk());
    }

    @Test
    public void methodWithMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum2MethodBad";
        final String classPath = "/classtemplates/ClassWithEnum2MethodBad.java";

        CompilationResult compilationResult = compileClass(className, classPath);
        String diagnosticsAsString = compilationResult.getDiagnosticsAsString();
        Assert.assertFalse(diagnosticsAsString, compilationResult.isOk());
        Assert.assertTrue(diagnosticsAsString, diagnosticsAsString.contains("error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2MethodBad, method: someMethod are not covered"));

        // check without annotation processor
        compilationResult = compileClass(className, classPath, true);
        Assert.assertTrue(compilationResult.getDiagnosticsAsString(), compilationResult.isOk());
    }

    @Test
    public void methodWithoutMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum2MethodGood";
        final String classPath = "/classtemplates/ClassWithEnum2MethodGood.java";

        CompilationResult compilationResult = compileClass(className, classPath);
        Assert.assertTrue(compilationResult.getDiagnosticsAsString(), compilationResult.isOk());

        // check without annotation processor
        compilationResult = compileClass(className, classPath, true);
        Assert.assertTrue(compilationResult.getDiagnosticsAsString(), compilationResult.isOk());
    }
}

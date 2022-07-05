package ru.hixon.switchexhaustivenesschecker.test;

import org.junit.Test;

import java.io.IOException;

public class ClassWithEnum2Test extends BaseTest {

    @Test
    public void constructorWithMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum2ConstructorBad";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorBad.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad, method: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad() are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches2Test() throws IOException {
        final String className = "ClassWithEnum2ConstructorBad2";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorBad2.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad2, method: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad2() are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches3Test() throws IOException {
        final String className = "ClassWithEnum2ConstructorBad3";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorBad3.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad3, method: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad3() are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches4Test() throws IOException {
        final String className = "ClassWithEnum2ConstructorBad4";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorBad4.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad4, method: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad4() are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches5Test() throws IOException {
        final String className = "ClassWithEnum2ConstructorBad5";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorBad5.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad5, method: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad5() are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches6Test() throws IOException {
        final String className = "ClassWithEnum2ConstructorBad6";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorBad6.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad6, method: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad6() are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches7Test() throws IOException {
        final String className = "ClassWithEnum2ConstructorBad7";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorBad7.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad7, method: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad7() are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches8Test() throws IOException {
        final String className = "ClassWithEnum2ConstructorBad8";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorBad8.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad8, method: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2ConstructorBad8() are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithoutMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum2ConstructorGood";
        final String classPath = "/classtemplates/ClassWithEnum2ConstructorGood.java";

        withoutMissedBranchesTest(className, classPath);
    }

    @Test
    public void methodWithMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum2MethodBad";
        final String classPath = "/classtemplates/ClassWithEnum2MethodBad.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2MethodBad, method: someMethod are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void methodWithMissedBranches2Test() throws IOException {
        final String className = "ClassWithEnum2MethodBad2";
        final String classPath = "/classtemplates/ClassWithEnum2MethodBad2.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2MethodBad2, method: someMethod are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void methodWithMissedBranches3Test() throws IOException {
        final String className = "ClassWithEnum2MethodBad3";
        final String classPath = "/classtemplates/ClassWithEnum2MethodBad3.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2MethodBad3, method: someMethod are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void methodWithMissedBranches4Test() throws IOException {
        final String className = "ClassWithEnum2MethodBad4";
        final String classPath = "/classtemplates/ClassWithEnum2MethodBad4.java";
        final String errorMessage = "error: Some switch branches in class: ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum2MethodBad4, method: someMethod are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void methodWithoutMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum2MethodGood";
        final String classPath = "/classtemplates/ClassWithEnum2MethodGood.java";

        withoutMissedBranchesTest(className, classPath);
    }

    @Test
    public void methodWithoutMissedBranches2Test() throws IOException {
        final String className = "ClassWithEnum2MethodGood2";
        final String classPath = "/classtemplates/ClassWithEnum2MethodGood2.java";

        withoutMissedBranchesTest(className, classPath);
    }

    @Test
    public void methodWithoutMissedBranches3Test() throws IOException {
        final String className = "ClassWithEnum2MethodGood3";
        final String classPath = "/classtemplates/ClassWithEnum2MethodGood3.java";

        withoutMissedBranchesTest(className, classPath);
    }

    @Test
    public void methodWithoutMissedBranches4Test() throws IOException {
        final String className = "ClassWithEnum2MethodGood4";
        final String classPath = "/classtemplates/ClassWithEnum2MethodGood4.java";

        withoutMissedBranchesTest(className, classPath);
    }
}

package ru.hixon.switchexhaustivenesschecker.test;

import org.junit.Test;

import java.io.IOException;

public class ClassWithEnum8Test extends BaseTest {

    @Test
    public void constructorWithMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum8ConstructorBad";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorBad.java";
        final String errorMessage = "error: Switch branches: [EnumVal5] in class: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad], constructor: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad()] are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches2Test() throws IOException {
        final String className = "ClassWithEnum8ConstructorBad2";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorBad2.java";
        final String errorMessage = "error: Switch branches: [EnumVal8] in class: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad2], constructor: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad2()] are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches3Test() throws IOException {
        final String className = "ClassWithEnum8ConstructorBad3";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorBad3.java";
        final String errorMessage = "error: Switch branches: [EnumVal7] in class: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad3], constructor: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad3()] are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches4Test() throws IOException {
        final String className = "ClassWithEnum8ConstructorBad4";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorBad4.java";
        final String errorMessage = "error: Switch branches: [EnumVal2] in class: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad4], constructor: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad4()] are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches5Test() throws IOException {
        final String className = "ClassWithEnum8ConstructorBad5";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorBad5.java";
        final String errorMessage = "error: Switch branches: [EnumVal7] in class: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad5], constructor: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad5()] are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches6Test() throws IOException {
        final String className = "ClassWithEnum8ConstructorBad6";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorBad6.java";
        final String errorMessage = "error: Switch branches: [EnumVal6, EnumVal4, EnumVal2] in class: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad6], constructor: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad6()] are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches7Test() throws IOException {
        final String className = "ClassWithEnum8ConstructorBad7";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorBad7.java";
        final String errorMessage = "error: Switch branches: [EnumVal6, EnumVal4, EnumVal5, EnumVal3] in class: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad7], constructor: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad7()] are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithMissedBranches8Test() throws IOException {
        final String className = "ClassWithEnum8ConstructorBad8";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorBad8.java";
        final String errorMessage = "error: Switch branches: [EnumVal6, EnumVal4, EnumVal5, EnumVal3] in class: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad8], constructor: [ru.hixon.switchexhaustivenesschecker.test.data.ClassWithEnum8ConstructorBad8()] are not covered";

        withMissedBranchesBase(className, classPath, errorMessage);
    }

    @Test
    public void constructorWithoutMissedBranchesTest() throws IOException {
        final String className = "ClassWithEnum8ConstructorGood";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorGood.java";

        withoutMissedBranchesTest(className, classPath);
    }

    @Test
    public void constructorWithoutMissedBranches2Test() throws IOException {
        final String className = "ClassWithEnum8ConstructorGood2";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorGood2.java";

        withoutMissedBranchesTest(className, classPath);
    }

    @Test
    public void constructorWithoutMissedBranches3Test() throws IOException {
        final String className = "ClassWithEnum8ConstructorGood3";
        final String classPath = "/classtemplates/ClassWithEnum8ConstructorGood3.java";

        withoutMissedBranchesTest(className, classPath);
    }
}

package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

public class ClassWithEnum2MethodGood7 {

    public enum Enum2 {
        EnumVal1,
        EnumVal2
    }

    @SwitchExhaustive
    public static class AnotherClass {
        public void someMethod(Enum2 enum2, int a) {
            switch (enum2) {
                case EnumVal1:
                    break;
                case EnumVal2:
                    break;
                default:
                    System.out.println("we don't care about default");
            }
        }
    }

    public static class AnotherClass2 {
        public void someMethod(Enum2 enum2, int a) {
            switch (enum2) {
                case EnumVal1:
                    break;
                default:
                    System.out.println("we don't care about default");
            }
        }
    }
}

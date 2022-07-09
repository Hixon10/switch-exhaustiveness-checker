package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

public class ClassWithEnum2MethodGood5 {

    public enum Enum2 {
        EnumVal1,
        EnumVal2
    }

    public void blabla(Enum2 enum2) {
        switch (enum2) {
            case EnumVal1:
                break;
            default:
                System.out.println("we don't care about defauts");
        }
    }

    public static class AnotherClass {
        @SwitchExhaustive
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
}

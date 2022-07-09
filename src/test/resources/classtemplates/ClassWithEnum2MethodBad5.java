package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

public class ClassWithEnum2MethodBad5 {

    public enum Enum2 {
        EnumVal1,
        EnumVal2
    }

    public static class AnotherClass {
        @SwitchExhaustive
        public void someMethod(Enum2 enum2) {
            switch (enum2) {
                case EnumVal1:
                    break;
                default:
                    System.out.println("we don't care about defauts");
            }
        }
    }
}

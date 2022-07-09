package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

@SwitchExhaustive
public class ClassWithEnum2ConstructorBad9 {

    public enum Enum2 {
        EnumVal1,
        EnumVal2
    }

    public static class AnotherClass {
        public AnotherClass(Enum2 enum2) {
            switch (enum2) {
                case EnumVal1:
                    break;
            }
        }
    }
}

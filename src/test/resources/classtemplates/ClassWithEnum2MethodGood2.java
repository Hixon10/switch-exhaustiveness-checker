package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

public class ClassWithEnum2MethodGood2 {

    public enum Enum2 {
        EnumVal1,
        EnumVal2
    }

    public void someMethod(Enum2 enum2) {
        switch (enum2) {
            case EnumVal1:
                break;
        }
    }

    @SwitchExhaustive
    public void someMethod(Enum2 enum2, int a) {
        switch (enum2) {
            case EnumVal1:
                break;
            case EnumVal2:
                break;
        }
    }
}

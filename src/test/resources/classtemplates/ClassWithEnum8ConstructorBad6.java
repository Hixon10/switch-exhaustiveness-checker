package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

@SwitchExhaustive
public class ClassWithEnum8ConstructorBad6 {

    public enum Enum8 {
        EnumVal1,
        EnumVal2,
        EnumVal3,
        EnumVal4,
        EnumVal5,
        EnumVal6,
        EnumVal7,
        EnumVal8
    }

    public ClassWithEnum8ConstructorBad6(Enum8 enum8) {
        switch (enum8) {
            case EnumVal1:
                break;
            case EnumVal3:
                break;
            case EnumVal5:
                break;
            case EnumVal7:
                break;
            case EnumVal8:
                break;
        }
    }
}

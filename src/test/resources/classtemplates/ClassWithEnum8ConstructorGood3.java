package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

@SwitchExhaustive
public class ClassWithEnum8ConstructorGood3 {

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

    public ClassWithEnum8ConstructorGood3(Enum8 enum8) {
        switch (enum8) {
            case EnumVal1:
            case EnumVal2:
            case EnumVal3:
            case EnumVal4:
            case EnumVal5:
            case EnumVal6:
            case EnumVal7:
            case EnumVal8:
                System.out.println("ClassWithEnum8ConstructorGood3");
        }
    }
}

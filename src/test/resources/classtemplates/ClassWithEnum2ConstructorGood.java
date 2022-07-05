package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

@SwitchExhaustive
public class ClassWithEnum2ConstructorGood {

    public enum Enum2 {
        EnumVal1,
        EnumVal2
    }

    public ClassWithEnum2ConstructorGood(Enum2 enum2) {
        switch (enum2) {
            case EnumVal1:
                break;
            case EnumVal2:
                break;
        }
    }
}

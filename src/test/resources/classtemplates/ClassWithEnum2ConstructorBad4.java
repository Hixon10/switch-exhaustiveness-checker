package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

@SwitchExhaustive
public class ClassWithEnum2ConstructorBad4 {

    public enum Enum2 {
        EnumVal1,
        EnumVal2
    }

    @SwitchExhaustive
    public ClassWithEnum2ConstructorBad4(Enum2 enum2) {
        switch (enum2) {
            case EnumVal1:
                break;
        }
    }

    public ClassWithEnum2ConstructorBad4() {
        Enum2 enum2 = Enum2.EnumVal1;
        switch (enum2) {
            case EnumVal1:
                break;
            case EnumVal2:
                break;
        }
    }
}

package ru.hixon.switchexhaustivenesschecker.test.data;

import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

@SwitchExhaustive
public class ClassWithEnum2ConstructorBad2 {

    public enum Enum2 {
        EnumVal1,
        EnumVal2
    }

    public ClassWithEnum2ConstructorBad2() {
        Enum2 enum2 = Enum2.EnumVal1;
        switch (enum2) {
            case EnumVal1:
                break;
            case EnumVal2:
                break;
        }
    }

    public ClassWithEnum2ConstructorBad2(Enum2 enum2) {
        switch (enum2) {
            case EnumVal1:
                break;
        }
    }
}

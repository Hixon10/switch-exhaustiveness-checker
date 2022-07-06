package ru.hixon.switchexhaustivenesschecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Put this annotation to Classes, Methods and Constructors to verify,
 * that every case in switch statements for Enums are covered.
 *
 * It is worth to mention, the annotation processor doesn't count `default`-case.
 * No matter whether you have `default`-case in your switch-statement, or not,
 * you need to cover all enum constants, if you use this annotation.
 */
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface SwitchExhaustive {

}

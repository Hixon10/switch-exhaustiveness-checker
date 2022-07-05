# Annotation processor for switch exhaustiveness checks in Java

This annotation processor allows you to check, that you cover all `switch-cases` for Enum switch statements. 

Switch exhaustiveness has already been implemented in [https://openjdk.org/jeps/361](https://openjdk.org/jeps/361). 
However, there are a few "drawbacks" of it:
1. You need to use `Java 14`, whereas this annotation processor allows you to have `Java 8`.
2. `JEP 361` is implemented for `switch expressions` (which is totally fine, though), whereas this annotation processor allows you to have `switch statements` instead of. 

## How to use

1. Include to your project and enable `SwitchExhaustiveCheckerProcessor` annotation processor. For example, *for gradle*:
```groovy
repositories {
    // ...
    mavenCentral()
    // ...
}

dependencies {
    // ....
    compileOnly 'ru.hixon:switch-exhaustiveness-checker:1.0'
    annotationProcessor 'ru.hixon:switch-exhaustiveness-checker:1.0'
    // ...
}
```
2. Put annotation `ru.hixon.switchexhaustivenesschecker.SwitchExhaustive` to needed constructors, methods and classes:
```java
import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

@SwitchExhaustive
public class ClassWithEnum2ConstructorBad {

    public enum Enum2 {
        EnumVal1,
        EnumVal2
    }

    public ClassWithEnum2ConstructorBad(Enum2 enum2) {
        switch (enum2) {
            case EnumVal1:
                break;
        }
    }
}
```

## Todo 

- [ ] User friendly error messages
- [ ] Publish annotation processor to maven central
- [ ] Unit tests for all possibly cases
- [ ] Integration tests for gradle
- [ ] Integration tests for maven
- [ ] Integration tests for spring
- [ ] Integration tests for different versions of Java
- [ ] Documentation for how to use with maven

## Credits
1. [https://github.com/svbrunov](https://github.com/svbrunov) - for helping with implementation of the processor.
2. [https://github.com/skinny85/jilt](https://github.com/skinny85/jilt) - for an annotation processor example. 
# Annotation processor for switch exhaustiveness checks in Java

This annotation processor allows you to check, that you cover all `switch-cases` for Enum switch statements. 

Switch exhaustiveness has already been implemented in [https://openjdk.org/jeps/361](https://openjdk.org/jeps/361). 
However, there are a few "drawbacks" of it:
1. You need to use `Java 14`, whereas this annotation processor allows you to have `Java 8`.
2. `JEP 361` is implemented for `switch expressions` (which is totally fine, though), whereas this annotation processor allows you to have `switch statements` instead of. 

## How to use in gradle

1. Include to your project and enable `SwitchExhaustiveCheckerProcessor` annotation processor. For example, *for gradle*:
```groovy
repositories {
    // ...
    mavenCentral()
    // ...
}

dependencies {
    // ....
    compileOnly 'ru.hixon:switch-exhaustiveness-checker:1.1'
    annotationProcessor 'ru.hixon:switch-exhaustiveness-checker:1.1'
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

## How to use in maven
1. Add optional dependency to your dependencies:
```xml
<dependencies>
    <dependency>
        <groupId>ru.hixon</groupId>
        <artifactId>switch-exhaustiveness-checker</artifactId>
        <version>1.1</version>
        <optional>true</optional>
    </dependency>
</dependencies>
```

2. Enable the annotation processor:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.10.1</version>
            <configuration>
                <annotationProcessors>
                    <annotationProcessor>
                        ru.hixon.switchexhaustivenesschecker.SwitchExhaustiveCheckerProcessor
                    </annotationProcessor>
                </annotationProcessors>
            </configuration>
        </plugin>
    </plugins>
</build>
```

3. Put annotation `ru.hixon.switchexhaustivenesschecker.SwitchExhaustive` to needed constructors, methods and classes:
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

## How it works 
An annotation processor `SwitchExhaustiveCheckerProcessor` processes all classes and methods, 
which are annotated by `@SwitchExhaustive`. The processor finds all switch statements, 
and after that verifies, that all `cases` for `Enum-constants` are covered.

The annotation processor is executed as one of compilation step. Hence you can be sure, that 
all `cases` for Enums are covered, if compilation process of your program has completed successfully.

## How to build and upload a new version to maven central
1. Specify correct credentials in `gradle.properties` (you can find it in the root folder of project).
2. Execute `./gradlew clean build uploadArchives`
3. Release the artifact - [https://central.sonatype.org/publish/release/](https://central.sonatype.org/publish/release/).

## Todo 

- [ ] User friendly error messages
- [X] Publish annotation processor to maven central
- [X] Unit tests for all possibly cases
- [ ] Integration tests for gradle
- [ ] Integration tests for maven
- [ ] Integration tests for spring
- [ ] Integration tests for different versions of Java

## Credits
1. [https://github.com/svbrunov](https://github.com/svbrunov) - for helping with implementation of the processor.
2. [https://github.com/skinny85/jilt](https://github.com/skinny85/jilt) - for an annotation processor example. 
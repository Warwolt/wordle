# Requirements
- Java 8 capable compiler
- Optionally: Gradle version 7.2 installed

# Building
The project can either be built with Gradle directly or with the wrapper `gradlew`. **If the Gradle wrapper doesn't work, you should install Gradle directly**.

### Linux and Mac

```
gradle jar && java -Dfile.encoding=utf-8 -jar build/libs/wordle.jar
```

or

```
./gradlew jar && java -Dfile.encoding=utf-8 -jar build/libs/wordle.jar
```

### Windows:

```
gradle jar && java -Dfile.encoding=utf-8 -jar build/libs/wordle.jar
```

or

```
./gradlew.bat jar && java -Dfile.encoding=utf-8 -jar build/libs/wordle.jar
```

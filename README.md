# Building
On Linux and Mac:

```
gradle run
```

On Windows (due to a bug in Gradle that garbles unicode characters):

```
gradle jar && java -Dfile.encoding=utf-8 -jar build/libs/unit_testing_course.jar
```

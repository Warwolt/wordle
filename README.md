# Building
On Linux and Mac:

```
gradle run
```

On Windows (due to a bug in Gradle that garbles unicode characters):

```
gradle assemble && java -Dfile.encoding=UTF8 -cp bin/main/ com.wordle.Main
```

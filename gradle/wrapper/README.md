# Gradle Wrapper note

The source archive contains the Gradle 9.8.0 wrapper configuration but does not fabricate a binary `gradle-wrapper.jar`.

If the wrapper JAR/scripts are absent in your checkout, regenerate them once with an installed Gradle distribution:

```bash
gradle wrapper --gradle-version 9.8.0
```

Commit the generated wrapper JAR and scripts in the Git repository so normal consumers can use `./gradlew` without a local Gradle installation.

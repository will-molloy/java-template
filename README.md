# java-template

[![build](https://github.com/will-molloy/java-template/actions/workflows/build.yml/badge.svg?branch=main&event=push)](https://github.com/will-molloy/java-template/actions/workflows/build.yml)
[![codecov](https://codecov.io/gh/will-molloy/java-template/branch/main/graph/badge.svg)](https://codecov.io/gh/will-molloy/java-template)

template repo for Java (or Kotlin/Scala) Gradle projects

## Features

- JDK 21 ([Amazon Corretto](https://aws.amazon.com/corretto/))
- [Gradle 8](https://github.com/gradle/gradle) (Kotlin DSL)
- [GitHub Actions](https://github.com/features/actions) CI/CD
- Automatic code formatting via [Spotless](https://github.com/diffplug/spotless)
  - Java: [`google-java-format`](https://github.com/google/google-java-format)
  - Kotlin: [`ktfmt`](https://github.com/facebook/ktfmt)
  - Scala: [`scalafmt`](https://github.com/scalameta/scalafmt)
- Code style analysis via [Checkstyle](https://github.com/checkstyle/checkstyle)
- Static analysis via [SpotBugs](https://spotbugs.github.io/)
- Unit and integration test support via [JUnit 5](https://junit.org/junit5/) and [TestSets plugin](https://github.com/unbroken-dome/gradle-testsets-plugin)
- Code coverage reporting via [Codecov](https://codecov.io/)
- Dependency upgrades via [Renovate bot](https://renovatebot.com)

## Usage

- Click [Use this template](https://github.com/will-molloy/java-template/generate)
  - This will prompt you to create a new repo with all the files setup
- Rename the root project (currently `java-template`) and group (currently `com.willmolloy`) to your liking
- Delete anything you won't use
- Update the README
- Other non-code setup like GitHub branch protections
- _**NOTE:** if creating a private repo, you probably want to disable the windows and mac builds_

### Build and test

```bash
./gradlew spotlessApply build integrationTest
```

### Upgrade Gradle

- Find the version number on the [releases page](https://gradle.org/releases/)
  - [Java Compatibility Matrix](https://docs.gradle.org/current/userguide/compatibility.html)
- Run: `./gradlew wrapper --gradle-version <VERSION> --distribution-type all`

### Upgrade Java

- Download the JDK using IntelliJ
- Update all references

## Promise

- Keep up to date with:
  - Future Java versions
  - Future Gradle versions
  - Other dependency upgrades
  - Any other cool tools or plugins

___

Generated from [java-template](https://github.com/will-molloy/java-template)

# java-gradle-template

[![build](https://github.com/wilmol/java-gradle-template/workflows/build/badge.svg?branch=main&event=push)](https://github.com/wilmol/java-gradle-template/actions?query=workflow%3Abuild)
[![integration-test](https://github.com/wilmol/java-gradle-template/workflows/integration-test/badge.svg?branch=main&event=push)](https://github.com/wilmol/java-gradle-template/actions?query=workflow%3Aintegration-test)
[![codecov](https://codecov.io/gh/wilmol/java-gradle-template/branch/main/graph/badge.svg)](https://codecov.io/gh/wilmol/java-gradle-template)

template repository for Java projects using Gradle

## Features

- Java 17
- Gradle 7
- [GitHub Actions](https://help.github.com/actions/language-and-framework-guides/building-and-testing-java-with-gradle) CICD
- Automatic code formatting via [Spotless](https://github.com/diffplug/spotless)
- Code style analysis via [Checkstyle](https://github.com/checkstyle/checkstyle)
- Static analysis via [SpotBugs](https://spotbugs.github.io/)
- Unit and integration test support via JUnit 5 and [TestSets plugin](https://github.com/unbroken-dome/gradle-testsets-plugin)
- Code coverage reporting via [Codecov](https://codecov.io/)

## Usage

- Click [Use this template](https://github.com/wilmol/java-gradle-template/generate)
  - This will prompt you to create a new repository with all the files setup
- Rename the root project (currently `java-gradle-template`) and group (currently `com.wilmol`) to your liking
- Delete anything you won't use (sub projects, dependencies, etc.)
- Update the README
- Other non-code setup like your GitHub branch protections

### Build and test

```
./gradlew spotlessApply build integrationTest
```

## Promise

- Keep up to date with:
  - Future Java versions
  - Future Gradle versions
  - Other dependency upgrades
  - Any other cool tools or plugins

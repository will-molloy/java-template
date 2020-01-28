[![Build Status](https://travis-ci.com/wilmol/java-gradle-template.svg?branch=master)](https://travis-ci.com/wilmol/java-gradle-template)
[![codecov](https://codecov.io/gh/wilmol/java-gradle-template/branch/master/graph/badge.svg)](https://codecov.io/gh/wilmol/java-gradle-template)
[![GitHub issues](https://img.shields.io/github/issues/wilmol/java-gradle-template.svg)](https://github.com/wilmol/java-gradle-template/issues)
[![GitHub pull-requests](https://img.shields.io/github/issues-pr/wilmol/java-gradle-template.svg)](https://github.com/wilmol/java-gradle-template/pulls/)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://github.com/wilmol/java-gradle-template/graphs/commit-activity)
[![GitHub license](https://img.shields.io/github/license/wilmol/java-gradle-template.svg)](https://github.com/wilmol/java-gradle-template/blob/master/LICENSE)

# java-gradle-template
template repository for Java projects using Gradle

## Usage
* Just go to: https://github.com/wilmol/java-gradle-template/generate
  * This will prompt you to create a new repository with all the files setup
* Rename the root project (currently `java-gradle-template`) and group (currently `com.wilmol`) to your liking 
* Create your README
* Delete anything you won't use (sub projects, dependencies etc.)

### Build
```
./gradlew spotlessApply build
```

## Features
* Java 11
* Gradle 6
  * Multi-project builds
* [Travis CI](https://travis-ci.com/) integration
* [Codecov](https://codecov.io/) integration
* [Spotless](https://github.com/diffplug/spotless) integration 
  * With [google-java-format](https://github.com/google/google-java-format)
* [Checkstyle](https://github.com/checkstyle/checkstyle) integration 
  * With [google_checks](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)
* [SpotBugs](https://spotbugs.github.io/) integration

## Promise
* Keep up to date with:
  * Future Java LTS versions
  * Future Gradle versions
  * Other dependency upgrades

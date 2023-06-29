logger.quiet "Java version: ${System.properties["java.version"]}"
logger.quiet "Gradle version: $gradle.gradleVersion"

buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath group: "com.diffplug.spotless", name: "spotless-plugin-gradle", version: "6.19.0"
    classpath group: "com.github.spotbugs.snom", name: "spotbugs-gradle-plugin", version: "5.0.14"
    classpath group: "com.asarkar.gradle", name: "build-time-tracker", version: "4.3.0"
    classpath group: "org.unbroken-dome.gradle-plugins", name: "gradle-testsets-plugin", version: "4.0.0"
  }
}

// Build time tracker
apply plugin: "com.asarkar.gradle.build-time-tracker"

allprojects {
  group "com.willmolloy"
  repositories {
    mavenCentral()
  }
}

subprojects {
  apply plugin: "java"
  sourceCompatibility = 19
  targetCompatibility = 19

  // Spotless (code formatting/linting)
  apply plugin: "com.diffplug.spotless"
  spotless {
    java {
      removeUnusedImports()
      googleJavaFormat()
    }
  }

  // Checkstyle (static analysis - code quality/style)
  apply plugin: "checkstyle"
  checkstyle {
    toolVersion = "10.12.0"
    configFile = rootProject.file("./checkstyle.xml")
    maxErrors = 0
    maxWarnings = 0
    ignoreFailures = false
  }

  // SpotBugs (static analysis - find possible bugs, performance issues etc.)
  apply plugin: "com.github.spotbugs"
  spotbugs {
    effort = "max"
    reportLevel = "low"
    ignoreFailures = false
    excludeFilter = rootProject.file("./spotbugs-exclude.xml")
  }
  tasks.withType(com.github.spotbugs.snom.SpotBugsTask) {
    reports {
      html.enabled = true
      xml.enabled = false
    }
  }

  // Tests
  tasks.withType(Test) {
    // run tests in parallel, assumes they"re threadsafe
    maxParallelForks = Runtime.runtime.availableProcessors().intdiv(2) ?: 1
    // use JUnit 5 engine
    useJUnitPlatform()
    testLogging {
      events = ["failed", "skipped"]
      // log the full failure messages
      exceptionFormat = "full"
      showExceptions = true
      showCauses = true
      showStackTraces = true
      // log the overall results (based on https://stackoverflow.com/a/36130467/6122976)
      afterSuite { desc, result ->
        if (!desc.parent) { // will match the outermost suite
          println("Results: ${result.resultType} " +
              "(${result.testCount} test${result.testCount > 1 ? "s" : ""}, " +
              "${result.successfulTestCount} passed, " +
              "${result.failedTestCount} failed, " +
              "${result.skippedTestCount} skipped)")
        }
      }
    }
  }

  // JaCoCo (code coverage reporting)
  apply plugin: "jacoco"
  tasks.withType(JacocoReport) {
    reports {
      xml.enabled = true
      html.enabled = true
      csv.enabled = false
    }
  }
  test.finalizedBy jacocoTestReport

  // Integration test support
  apply plugin: "org.unbroken-dome.test-sets"
  testSets {
    integrationTest
  }
  integrationTest.finalizedBy jacocoIntegrationTestReport

  // pin dependency versions
  ext {
    // production
    log4jVersion = "2.20.0"
    guavaVersion = "32.0.1-jre"

    // test
    junitVersion = "5.9.3"
    truthVersion = "1.1.4"
    mockitoVersion = "5.3.1"
  }

  dependencies {
    implementation group: "org.apache.logging.log4j", name: "log4j-core", version: "$log4jVersion"
    implementation group: "com.github.spotbugs", name: "spotbugs-annotations", version: "4.7.3"
    implementation group: "com.google.guava", name: "guava", version: "$guavaVersion"

    testImplementation group: "org.junit.jupiter", name: "junit-jupiter", version: "$junitVersion"
    testImplementation group: "com.google.truth", name: "truth", version: "$truthVersion"
    testImplementation group: "com.google.truth.extensions", name: "truth-java8-extension", version: "$truthVersion"
    testImplementation group: "org.mockito", name: "mockito-core", version: "$mockitoVersion"
    testImplementation group: "org.mockito", name: "mockito-junit-jupiter", version: "$mockitoVersion"
  }

  // dependency cleanup, exclusions and resolutions
  configurations.all {
    exclude group: "org.assertj" // using truth instead
    resolutionStrategy {
      force "com.google.guava:guava:$guavaVersion" // so the android version isn"t pulled in
    }
  }
}

import com.diffplug.gradle.spotless.SpotlessExtension
import com.github.spotbugs.snom.Confidence
import com.github.spotbugs.snom.Effort
import com.github.spotbugs.snom.SpotBugsExtension
import com.github.spotbugs.snom.SpotBugsTask
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

logger.quiet("Java version: ${JavaVersion.current()}")
logger.quiet("Gradle version: ${gradle.gradleVersion}")

plugins {
  id("java-library")
  id("com.diffplug.gradle.spotless") version "6.19.0" apply (false)
  id("com.github.spotbugs") version "5.0.14" apply (false)
  id("com.asarkar.gradle.build-time-tracker") version "4.3.0"
  id("org.unbroken-dome.test-sets") version "4.0.0" apply (false)
}

allprojects {
  group = "com.willmolloy"
  repositories {
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "java")
  configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
  }

  // Spotless (code formatting/linting)
  apply(plugin = "com.diffplug.spotless")
  configure<SpotlessExtension> {
    java {
      removeUnusedImports()
      googleJavaFormat()
    }
  }

  // Checkstyle (static analysis - code quality/style)
  apply(plugin = "checkstyle")
  configure<CheckstyleExtension> {
    toolVersion = "10.12.0"
    configFile = rootProject.file("./checkstyle.xml")
    maxErrors = 0
    maxWarnings = 0
    isIgnoreFailures = false
  }

  // SpotBugs (static analysis - find possible bugs, performance issues etc.)
  // TODO working? test on another project
  apply(plugin = "com.github.spotbugs")
  configure<SpotBugsExtension> {
    effort.set(Effort.MAX)
    reportLevel.set(Confidence.LOW)
    ignoreFailures.set(false)
    excludeFilter.set(rootProject.file("./spotbugs-exclude.xml"))
  }
  tasks.withType<SpotBugsTask> {
    reports.create("xml").required.set(true)
    reports.create("html").required.set(true)
  }

  tasks.withType<Test> {
    // run tests in parallel, assumes they"re threadsafe
    maxParallelForks = Runtime.getRuntime().availableProcessors()
    // use JUnit 5 engine
    useJUnitPlatform()
    testLogging {
      events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED)
      // log the full failure messages
      exceptionFormat = TestExceptionFormat.FULL
      showExceptions = true
      showCauses = true
      showStackTraces = true
      // log the overall results (based on https://stackoverflow.com/a/36130467/6122976)
      afterSuite(KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
        if (desc.parent == null) { // will match the outermost suite
          println(
            "Results: ${result.resultType} " +
                "(${result.testCount} test${if (result.testCount > 1) "s" else ""}, " +
                "${result.successfulTestCount} passed, " +
                "${result.failedTestCount} failed, " +
                "${result.skippedTestCount} skipped)"
          )
        }
      }))
    }
    finalizedBy(tasks.withType<JacocoReport>())
  }

  // JaCoCo (code coverage reporting)
  apply(plugin = "jacoco")
  tasks.withType<JacocoReport> {
    reports {
      xml.required.set(true)
      html.required.set(true)
      csv.required.set(false)
    }
  }

  // Integration test support
  // TODO test-set plugin

  dependencies {
    val log4jVersion = "2.20.0"
    val guavaVersion = "32.0.1-jre"
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("com.github.spotbugs:spotbugs-annotations:4.7.3")
    implementation("com.google.guava:guava:$guavaVersion")

    val junitVersion = "5.9.3"
    val truthVersion = "1.1.4"
    val mockitoVersion = "5.3.1"
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("com.google.truth:truth:$truthVersion")
    testImplementation("com.google.truth.extensions:truth-java8-extension:$truthVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")

    // dependency cleanup
    configurations.all {
      exclude("org.assertj") // using truth instead
      exclude("junit") // using junit5
      resolutionStrategy {
        force("com.google.guava:guava:$guavaVersion") // so the android version isn't pulled in
      }
    }
  }
}
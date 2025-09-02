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
  alias(libs.plugins.spotless)
  alias(libs.plugins.spotbugs)
  alias(libs.plugins.buildtimetracker)
}

allprojects {
  group = "com.willmolloy"
  repositories {
    mavenCentral()
  }

  apply(plugin = "java")
  configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }

  apply(plugin = "com.diffplug.spotless")
  configure<SpotlessExtension> {
    // https://github.com/diffplug/spotless/tree/main/plugin-gradle#java
    java {
      removeUnusedImports()
      googleJavaFormat()
      trimTrailingWhitespace()
      endWithNewline()
    }
    // https://github.com/diffplug/spotless/tree/main/plugin-gradle#kotlin
    kotlin {
      ktfmt().googleStyle()
      trimTrailingWhitespace()
      endWithNewline()
    }
    kotlinGradle {
      ktlint().editorConfigOverride(mapOf("ktlint_standard_no-empty-file" to "disabled"))
      trimTrailingWhitespace()
      endWithNewline()
    }
    // https://github.com/diffplug/spotless/tree/main/plugin-gradle#scala
    scala {
      scalafmt().configFile("$rootDir/scalafmt.conf")
      trimTrailingWhitespace()
      endWithNewline()
    }
  }

  // TODO Kotlin/Scala alternative?
  apply(plugin = "checkstyle")
  configure<CheckstyleExtension> {
    toolVersion = rootProject.libs.versions.checkstyle.get()
    configFile = rootProject.file("./checkstyle.xml")
    configProperties = mapOf("suppressionFile" to rootProject.file("./checkstyle-suppressions.xml"))
    maxErrors = 0
    maxWarnings = 0
    isIgnoreFailures = false
  }

  apply(plugin = "com.github.spotbugs")
  configure<SpotBugsExtension> {
    effort.set(Effort.MAX)
    reportLevel.set(Confidence.LOW)
    ignoreFailures.set(false)
    excludeFilter.set(rootProject.file("./spotbugs-exclude.xml"))
  }
  tasks.withType<SpotBugsTask> {
    reports.create("html").required.set(true)
  }

  tasks.withType<Test> {
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
    useJUnitPlatform()
    testLogging {
      events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED)
      exceptionFormat = TestExceptionFormat.FULL
      showExceptions = true
      showCauses = true
      showStackTraces = true
      afterSuite(
        KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
          if (desc.parent == null) {
            println(
              "Results: ${result.resultType} " +
                "(${result.testCount} test${if (result.testCount > 1) "s" else ""}, " +
                "${result.successfulTestCount} passed, " +
                "${result.failedTestCount} failed, " +
                "${result.skippedTestCount} skipped)",
            )
          }
        }),
      )
    }
    reports.html.required = false
    reports.junitXml.required = false
    finalizedBy(tasks.withType<JacocoReport>())
  }

  apply(plugin = "jacoco")
  tasks.withType<JacocoReport> {
    reports {
      xml.required.set(true)
    }
  }

  dependencies {
    implementation(rootProject.libs.spotbugs.annotations)

    testImplementation(rootProject.libs.junit)
    testImplementation(rootProject.libs.truth)
    testImplementation(rootProject.libs.mockito.core)
    testImplementation(rootProject.libs.mockito.junit)

    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")

    configurations.all {
      exclude(group = "org.assertj")
      exclude(group = "junit")
    }
  }
}

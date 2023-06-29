import com.diffplug.gradle.spotless.SpotlessExtension

logger.quiet("Java version: ${System.getProperty("java.version")}")
logger.quiet("Gradle version: $gradle.gradleVersion")

// TODO migrate to declarative plugins
buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("com.diffplug.spotless", "spotless-plugin-gradle", "6.19.0")
    classpath("com.github.spotbugs.snom", "spotbugs-gradle-plugin", "5.0.14")
    classpath("com.asarkar.gradle", "build-time-tracker", "4.3.0")
    classpath("org.unbroken-dome.gradle-plugins", "gradle-testsets-plugin", "4.0.0")
  }
}

apply(plugin = "com.asarkar.gradle.build-time-tracker")

allprojects {
  group = "com.willmolloy"
  repositories {
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "java")

  apply(plugin = "com.diffplug.spotless")
  configure<SpotlessExtension> {
    java {
      removeUnusedImports()
      googleJavaFormat()
    }
  }


}
plugins {
  kotlin("jvm") version libs.versions.kotlin
}

kotlin {
  jvmToolchain(21)
}

// TODO disabling on Kotlin/Scala atm... too many false positives
spotbugs {
  ignoreFailures.set(true)
}

plugins {
  kotlin("jvm") version libs.versions.kotlin
}

kotlin {
  jvmToolchain(25)
}

// TODO disabling on Kotlin/Scala atm... too many false positives
spotbugs {
  ignoreFailures.set(true)
}

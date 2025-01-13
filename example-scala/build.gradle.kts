plugins {
  scala
}

dependencies {
  implementation(libs.scala.library)
}

// TODO disabling on Kotlin/Scala atm... too many false positives
spotbugs {
  ignoreFailures.set(true)
}

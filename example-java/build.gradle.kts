plugins {
  alias(libs.plugins.testsets)
}

testSets {
  create("integrationTest")
}

dependencies {
  implementation(rootProject.libs.log4j.core)
  implementation(rootProject.libs.log4j.api)
  implementation(rootProject.libs.log4j.slf4j2)
}

package com.willmolloy

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/** Unit tests for [HelloKotlin]. */
class HelloKotlinTest {

  @Test
  fun `test hello`() {
    assertThat(HelloKotlin.hello("world")).isEqualTo("Hello world, from Kotlin!")
  }
}

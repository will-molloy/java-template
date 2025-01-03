package com.willmolloy

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/**
 * Unit tests for [HelloWorld].
 */
class HelloWorldTest {
  @Test
  fun `test hello`() {
    assertThat(HelloWorld().hello("world")).isEqualTo("Hello world!")
  }
}

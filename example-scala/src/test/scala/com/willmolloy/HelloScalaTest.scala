package com.willmolloy

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/** Unit tests for [[HelloScala]]. */
final class HelloScalaTest {

  @Test
  def test_hello(): Unit = {
    assertThat(HelloScala.hello("world")).isEqualTo("Hello world, from Scala!")
  }
}

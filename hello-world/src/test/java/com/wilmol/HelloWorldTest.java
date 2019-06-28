package com.wilmol;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Example test src.
 *
 * @author wilmol
 */
class HelloWorldTest {

  @Test
  void test() {
    assertThat(new HelloWorld().helloWorld()).isEqualTo("Hello world!");
  }
}

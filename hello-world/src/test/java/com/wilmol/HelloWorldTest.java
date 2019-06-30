package com.wilmol;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Example test src.
 *
 * <p>Created by wilmol on 2019-06-15.
 */
class HelloWorldTest {

  @Test
  void test() {
    assertThat(new HelloWorld().helloWorld()).isEqualTo("Hello world!");
  }
}

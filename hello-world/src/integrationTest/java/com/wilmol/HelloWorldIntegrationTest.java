package com.wilmol;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Example integration test.
 *
 * @author <a href=https://wilmol.com>Will Molloy</a>
 */
class HelloWorldIntegrationTest {

  @Test
  void test() {
    assertThat(new HelloWorld().helloWorld()).isEqualTo("Hello world!");
  }
}

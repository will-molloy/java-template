package com.willmolloy;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Integration tests for {@link HelloWorld}.
 *
 * @author <a href=https://willmolloy.com>Will Molloy</a>
 */
class HelloWorldIntegrationTest {

  @Test
  void test() {
    assertThat(new HelloWorld().hello("world")).isEqualTo("Hello world!");
  }
}

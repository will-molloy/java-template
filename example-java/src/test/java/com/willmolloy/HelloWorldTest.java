package com.willmolloy;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HelloWorld}.
 *
 * @author <a href=https://willmolloy.com>Will Molloy</a>
 */
class HelloWorldTest {

  @Test
  void test_hello() {
    assertThat(new HelloWorld().hello("world")).isEqualTo("Hello world!");
  }
}

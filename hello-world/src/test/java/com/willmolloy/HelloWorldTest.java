package com.willmolloy;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Example unit test.
 *
 * @author <a href=https://willmolloy.com>Will Molloy</a>
 */
class HelloWorldTest {

  @Test
  void test() {
    assertThat(new HelloWorld().hello("world")).isEqualTo("Hello world!");
  }
}

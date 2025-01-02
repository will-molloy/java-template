package com.willmolloy;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HelloJava}.
 *
 * @author <a href=https://willmolloy.com>Will Molloy</a>
 */
class HelloJavaTest {

  @Test
  void test_java() {
    assertThat(new HelloJava().hello("world")).isEqualTo("Hello world!");
  }
}

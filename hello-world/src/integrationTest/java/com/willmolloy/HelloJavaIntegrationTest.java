package com.willmolloy;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Integration tests for {@link HelloJava}.
 *
 * @author <a href=https://willmolloy.com>Will Molloy</a>
 */
class HelloJavaIntegrationTest {

  @Test
  void test() {
    assertThat(new HelloJava().hello("world")).isEqualTo("Hello world!");
  }
}

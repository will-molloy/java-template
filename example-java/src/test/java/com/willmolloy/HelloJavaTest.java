package com.willmolloy;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HelloJava}.
 *
 * @author Will
 */
class HelloJavaTest {

  @Test
  void test_hello() {
    assertThat(new HelloJava().hello("world")).isEqualTo("Hello world!");
  }
}

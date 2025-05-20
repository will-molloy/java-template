package com.willmolloy;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Integration tests for {@link HelloJava}.
 *
 * @author Will
 */
class HelloJavaIntegrationTest {

  @Test
  void test_hello() {
    assertThat(new HelloJava().hello("world")).isEqualTo("Hello world!");
  }
}

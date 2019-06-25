package com.wilmol;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

/**
 * Example test src.
 *
 * @author wilmol
 */
public class HelloWorldTest {

  @Test
  public void test() {
    assertThat(new HelloWorld().helloWorld()).isEqualTo("Hello world!");
  }
}

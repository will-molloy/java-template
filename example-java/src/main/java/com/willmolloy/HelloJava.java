package com.willmolloy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Example main src.
 *
 * @author Will
 */
final class HelloJava {
  private static final Logger log = LogManager.getLogger();

  String hello(String text) {
    log.debug("Hello {}!", text);
    return "Hello %s!".formatted(text);
  }
}

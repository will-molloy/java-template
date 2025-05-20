package com.willmolloy;

import static com.google.common.base.Preconditions.checkArgument;

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
    checkArgument(!text.isEmpty());
    log.debug("Hello {}!", text);
    return "Hello %s!".formatted(text);
  }
}

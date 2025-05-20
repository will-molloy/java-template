package com.willmolloy

/** Example main src. */
object HelloKotlin {

  fun hello(text: String): String {
    require(text.isNotEmpty())
    return "Hello $text, from Kotlin!"
  }
}

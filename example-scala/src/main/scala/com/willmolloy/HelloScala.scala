package com.willmolloy

/** Example main src. */
object HelloScala {

  def hello(text: String): String = {
    require(text.nonEmpty)
    s"Hello $text, from Scala!"
  }
}

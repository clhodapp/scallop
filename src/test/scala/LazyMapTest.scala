
package org.rogach.scallop

import org.scalatest.FunSuite
import org.rogach.scallop._

class LazyMapTest extends FunSuite {

  val m = Map(1 -> "a", 2 -> "b")

  var runCount = 0
  val lm = new LazyMap ({
    runCount += 1
    m
  })

  test("query") {
    assert(lm.get(1) === Some("a"))
  }

  test("include") {
    assert(
      (lm + (3 -> "c")) === Map(1 -> "a", 2 -> "b", 3 -> "c")
    )
  }

  test("exclude") {
    assert((lm - 2) === Map(1 -> "a"))
  }

  test("get underlying") {
    assert(lm.m === m)
  }

  test("lazyness") {
    assert(runCount === 1)
  }

}


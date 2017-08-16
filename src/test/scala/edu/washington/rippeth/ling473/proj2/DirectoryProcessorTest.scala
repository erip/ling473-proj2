package edu.washington.rippeth.ling473.proj2

import org.scalatest.{FlatSpec, Matchers}

class DirectoryProcessorTest extends FlatSpec with Matchers {
  private def getResource(resourceName: String) =
    getClass.getResource(resourceName).getPath

  "A DirectoryProcessor" should "aggregate counts" in {
    val directoryProcessor = new DirectoryProcessor(getResource("/basic/"))
    val counts = directoryProcessor.counts
    assertResult(2) {
      counts.size
    }

    assertResult(1) {
      counts("hello")
    }

    assertResult(1) {
      counts("world")
    }
  }
}

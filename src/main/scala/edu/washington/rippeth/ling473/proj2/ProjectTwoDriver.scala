package edu.washington.rippeth.ling473.proj2

object ProjectTwoDriver extends App {
  new DirectoryProcessor(args.head).outputDescendingCounts()
}

package edu.washington.rippeth.ling473.proj2

import java.io.File

import scalaz.Scalaz._

class DirectoryProcessor(dir: String) {

  private class DirectoryReader(dir: String) {
    val files: Seq[File] = new File(dir) match {
      case d if d.exists && d.isDirectory => d.listFiles.filter(_.isFile)
      case _ => Nil
    }
  }

  private val files: Seq[File] = new DirectoryReader(dir).files

  private def processDirectory(files: Seq[File]): Map[String, Int] =
  // create a FileProcessor for each file in the directory
    files.map(file => new FileProcessor(file))
      // merge the unigram counts into a single map
      .foldLeft(Map[String, Int]()) { (acc, fileProcessor) =>
      acc |+| fileProcessor.countWordsInFile
    }

   def printWordCountPerFile(word: String): Unit =
    files.foreach( file =>
      println(s"$file:  ${new FileProcessor(file).countWordsInFile(word)}")
    )


  val counts: Map[String, Int] = processDirectory(files)

  def outputDescendingCounts(): Unit =
    counts.toSeq.sortBy(-_._2).foreach { case (k, v) =>
      println(s"$k\t$v")
    }
}

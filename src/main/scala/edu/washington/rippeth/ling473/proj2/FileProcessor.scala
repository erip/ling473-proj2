package edu.washington.rippeth.ling473.proj2

import java.io.File

import scala.io.Source
import scala.util.matching.Regex

class FileProcessor(val lines: Iterator[String]) {

  def this(file: File) = this(Source.fromFile(file).getLines)

  private implicit val wordRegex: Regex = "[A-Za-z']+".r

  private val charIsApostrophe: Char => Boolean = _ == '\''
  private val dropWhileFirstCharIsApostrophe: String => String = { s => s.dropWhile(charIsApostrophe) }

  private def removeApostrophesFromFrontAndBack(s: String): String =
    dropWhileFirstCharIsApostrophe(dropWhileFirstCharIsApostrophe(s).reverse).reverse

  private def parseAllWordsByRegex(s: String)(implicit regex: Regex) =
    regex.findAllIn(s).matchData.map(m => m.group(0))

  def countWordsInFile: Map[String, Int] =
    // for each line in the file
    lines
    // split the trimmed line on whitespace
    .flatMap(_.trim.split("\\s+"))
    // remove all tokens starting with '<' and ending with '>'
    .filterNot(tok => tok.startsWith("<") && tok.endsWith(">"))
    // parse all matches from the wordRegex
    .flatMap(parseAllWordsByRegex)
    // remove all prefixed and suffixed apostrophes
    .map(removeApostrophesFromFrontAndBack)
    // lowercase all remaining tokens
    .map(_.toLowerCase)
    // remove all empty or space-only strings
    .filterNot(_.trim.isEmpty)
    // and group by word, counting all characters in each group
    .foldLeft(Map[String, Int]())(
      (acc, word) => acc + (word -> (acc.getOrElse(word, 0) + 1))
    )
}

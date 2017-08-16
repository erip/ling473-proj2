package edu.washington.rippeth.ling473.proj2

import org.scalatest.{FlatSpec, Matchers}

class FileProcessorTest extends FlatSpec with Matchers {

  private def countWords(lines: Iterator[String]): Map[String, Int] =
    new FileProcessor(lines).countWordsInFile

  private def undefinedWordFailure(word: String) = fail(s"'$word' should be defined")

  "A FileProcessor" should "find no words" in {
    val lines =
      """
        |
      """.stripMargin.lines

    val counts = countWords(lines)
    assert(counts.isEmpty)
  }

  "A FileProcessor" should "not count SGML tags" in {
    val lines =
      """
        |<Invisible> </Invisible>
      """.stripMargin.lines

    val counts = countWords(lines)
    assert(counts.isEmpty)
  }

  "A FileProcessor" should "count metadata" in {
    val word = "ql"
    val lines =
      s"""
        |&$word;
      """.stripMargin.lines

    val counts = countWords(lines)
    assert(counts.isDefinedAt(word.toLowerCase))
    assertResult(1) {
      counts(word.toLowerCase)
    }
  }

  "A FileProcessor" should "not count numbers" in {
    val lines =
      """
        |09-13 0456
      """.stripMargin.lines

    val counts = countWords(lines)
    assert(counts.isEmpty)
  }

  "A FileProcessor" should "count words between tags" in {
    val word1 = "Hello"
    val word2 = "World"

    val lines =
      s"""
        |<GREETING> $word1, $word2! </GREETING>
      """.stripMargin.lines

    val counts = countWords(lines)

    assertResult(2) {
      counts.size
    }

    assertResult(1) {
      counts.getOrElse(word1.toLowerCase, undefinedWordFailure(word1.toLowerCase))
    }

    assertResult(1) {
      counts.getOrElse(word2.toLowerCase, undefinedWordFailure(word2.toLowerCase))
    }
  }

  "A FileProcessor" should "trim apostrophes" in {
    val word = "hello"

    val lines =
      s"""
        | ''''''$word''''''''
      """.stripMargin.lines

    val counts = countWords(lines)
    assert(counts.isDefinedAt(word.toLowerCase))
    assertResult(1) {
      counts(word.toLowerCase)
    }
  }

  "A FileProcessor" should "not trim internal apostrophes" in {
    val word = "Hello'World"

    val lines =
      s"""
         | $word
      """.stripMargin.lines

    val counts = countWords(lines)
    assert(counts.isDefinedAt(word.toLowerCase))
    assertResult(1) {
      counts(word.toLowerCase)
    }
  }

  "A FileProcessor" should "split up punctuation-separated words" in {
    val word = "N.Y."

    val lines =
      s"""
         | $word
      """.stripMargin.lines

    val counts = countWords(lines)

    assertResult(2) {
      counts.size
    }

    word.split('.').foreach { tok =>
      assertResult(1) {
        counts.getOrElse(tok.toLowerCase, undefinedWordFailure(tok.toLowerCase))
      }
    }
  }

  "A FileProcessor" should "count a word twice" in {
    val word = "Hello"
    val words = s"$word.$word."

    val lines =
      s"""
         | $word
      """.stripMargin.lines

    val counts = countWords(lines)

    assertResult(1) {
      counts.size
    }

    assertResult(1) {
      counts.getOrElse(word.toLowerCase, undefinedWordFailure(word.toLowerCase))
    }
  }

}

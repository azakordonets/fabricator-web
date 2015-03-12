package controllers

import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.mvc.{Action, Controller}

object WordsBack extends Controller{

  private val words = fabricator.Words()

  case class wordsSeq(words: Seq[String])
  case class sentencesSeq(sentences: Seq[String])
  case class paragraphsSeq(paragraphs: Seq[String])

  implicit def wordsWrites = Json.writes[wordsSeq]
  implicit def sentencesWrites = Json.writes[sentencesSeq]
  implicit def paragraphsWrites = Json.writes[paragraphsSeq]

  def getWords(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("word" -> words.word))) else Ok(words.word)
    } else {
      if (json) Ok(toJson(wordsSeq(words.words(amount)))) else Ok(toJson(words.words(amount)))
    }
  }

  def paragraph(length: Int, json: Boolean) = Action {
    if (json) Ok(toJson(Map("paragraph" -> words.paragraph(length)))) else Ok(words.paragraph(length))
  }

  def sentence(wordsAmount: Int, json: Boolean) = Action {
    if (json) Ok(toJson(Map("sentence" -> words.sentence(wordsAmount)))) else Ok(words.sentence(wordsAmount))
  }
}

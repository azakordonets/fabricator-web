package controllers

import play.api.mvc.Controller

object WordsBack extends Controller{

  private val words = fabricator.Words()

  def getWords(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def paragraph(length: Int, json: Boolean) = play.mvc.Results.TODO

  def sentence(words: Int, json: Boolean) = play.mvc.Results.TODO
}

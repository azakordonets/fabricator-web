package controllers

import play.api.mvc.Controller

object WordsBack extends Controller{

  private val words = fabricator.Words()

}

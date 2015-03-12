package controllers

import play.api.mvc.Controller

object FileBack extends Controller{
  
  private val file = fabricator.FileGenerator()

  def image(width: Int, height: Int) = play.mvc.Results.TODO

  def csv() = play.mvc.Results.TODO
}

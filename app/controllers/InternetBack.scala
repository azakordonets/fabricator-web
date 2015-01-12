package controllers

import play.api.mvc.Controller

object InternetBack extends Controller{
  
  private val internet = fabricator.Internet()

}

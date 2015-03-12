package controllers

import play.api.mvc.Controller

object MobileBack extends Controller{
  
  private val mobile = fabricator.Mobile()

  def android(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def apple(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def wp7(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def wp8(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def blackberry(json: Boolean, amount: Int) = play.mvc.Results.TODO
}

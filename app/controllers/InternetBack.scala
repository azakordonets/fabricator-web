package controllers

import play.api.mvc.Controller

object InternetBack extends Controller{
  
  private val internet = fabricator.Internet()


  def appleToken(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def url(protocol: String, host: String, callName: String, json: Boolean, amount: Int) = play.mvc.Results.TODO

  def ip(ipv6: Boolean, json: Boolean, amount: Int) = play.mvc.Results.TODO

  def macaddress(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def uuid(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def color(format: String, greyscale: Boolean, json: Boolean, amount: Int) = play.mvc.Results.TODO

  def twitter(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def hashtag(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def googleanalytics(json: Boolean, amount: Int) = play.mvc.Results.TODO

  def facebookid(json: Boolean, amount: Int) = play.mvc.Results.TODO

}

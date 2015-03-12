package controllers

import play.api.mvc.Controller

object LocationBack extends Controller{
  
  private val location = fabricator.Location()

  def altitude(max: Int, json: Boolean, amount: Int) = play.mvc.Results.TODO

  def depth(min: Int, json: Boolean, amount: Int) = play.mvc.Results.TODO

  def coordinates(accuracy: Int, json: Boolean, amount: Int) = play.mvc.Results.TODO

  def latitude(accuracy: Int, json: Boolean, amount: Int) = play.mvc.Results.TODO

  def longitude(accuracy: Int, json: Boolean, amount: Int) = play.mvc.Results.TODO

  def geohash(json: Boolean, amount: Int) = play.mvc.Results.TODO
}

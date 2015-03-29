package controllers

import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.mvc.{Action, Controller}

object LocationBack extends Controller{
  
  private val location = fabricator.Location()

  case class altitudesSeq(altitudes: Seq[String])
  case class depthsSeq(depths: Seq[String])
  case class coordinatesSeq(coordinates: Seq[String])
  case class latitudesSeq(latitudes: Seq[String])
  case class longitudesSeq(longitudes: Seq[String])
  case class geohashesSeq(geohashes: Seq[String])

  implicit def altitudesWrites= Json.writes[altitudesSeq]
  implicit def depthsWrites= Json.writes[depthsSeq]
  implicit def coordinatesWrites= Json.writes[coordinatesSeq]
  implicit def latitudesWrites= Json.writes[latitudesSeq]
  implicit def longitudesWrites= Json.writes[longitudesSeq]
  implicit def geohashessWrites= Json.writes[geohashesSeq]

  def altitude(max: Int, json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("altitude" -> location.altitude(max)))) else Ok(location.altitude(max))
    } else {
      val altitudes = Seq.fill(amount)(location.altitude(max))
      if (json) Ok(toJson(altitudesSeq(altitudes))) else Ok(toJson(altitudes))
    }
  }

  def depth(min: Int, json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("depth" -> location.depth(min)))) else Ok(location.depth(min))
    } else {
      val depths = Seq.fill(amount)(location.depth(min))
      if (json) Ok(toJson(depthsSeq(depths))) else Ok(toJson(depths))
    }
  }

  def coordinates(accuracy: Int, json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("coordinates" -> location.coordinates(accuracy)))) else Ok(location.coordinates(accuracy))
    } else {
      val coordinatess = Seq.fill(amount)(location.coordinates(accuracy))
      if (json) Ok(toJson(coordinatesSeq(coordinatess))) else Ok(toJson(coordinatess))
    }
  }

  def latitude(accuracy: Int, json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("latitude" -> location.latitude(accuracy)))) else Ok(location.latitude(accuracy))
    } else {
      val latitudes = Seq.fill(amount)(location.latitude(accuracy))
      if (json) Ok(toJson(latitudesSeq(latitudes))) else Ok(toJson(latitudes))
    }
  }

  def longitude(accuracy: Int, json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("longitude" -> location.longitude(accuracy)))) else Ok(location.longitude(accuracy))
    } else {
      val longitudes = Seq.fill(amount)(location.longitude(accuracy))
      if (json) Ok(toJson(longitudesSeq(longitudes))) else Ok(toJson(longitudes))
    }
  }

  def getGeohash(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("geohash" -> location.geohash))) else Ok(location.geohash)
    } else {
      val geohashs = Seq.fill(amount)(location.geohash)
      if (json) Ok(toJson(geohashesSeq(geohashs))) else Ok(toJson(geohashs))
    }
  }
}

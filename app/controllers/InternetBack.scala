package controllers

import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.mvc.{Action, Controller}


object InternetBack extends Controller {

  private val internet = fabricator.Internet()

  case class appleSeq(tokens: Seq[String])
  case class urlSeq(urls: Seq[String])
  case class ipSeq(ips: Seq[String])
  case class macAddressSeq(macAddresses: Seq[String])
  case class colorsSeq(colors: Seq[String])
  case class uuidsSeq(uuids: Seq[String])
  case class twittersSeq(twitters: Seq[String])
  case class hashTagsSeq(hashTags: Seq[String])
  case class googleAnalyticsSeq(googleAnalytics: Seq[String])
  case class facebookIdsSeq(facebookIds: Seq[String])

  implicit def appleTokensWrite = Json.writes[appleSeq]
  implicit def urlsWrite = Json.writes[urlSeq]
  implicit def ipsWrite = Json.writes[ipSeq]
  implicit def macAddresssWrite = Json.writes[macAddressSeq]
  implicit def colorssWrite = Json.writes[colorsSeq]
  implicit def uuidssWrite = Json.writes[uuidsSeq]
  implicit def twitterssWrite = Json.writes[twittersSeq]
  implicit def hashTagssWrite = Json.writes[hashTagsSeq]
  implicit def googleAnalyticssWrite = Json.writes[googleAnalyticsSeq]
  implicit def facebookIdssWrite = Json.writes[facebookIdsSeq]

  def appleToken(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("token" -> internet.appleToken))) else Ok(internet.appleToken)
    } else {
      val tokensSeq = Seq.fill(amount)(internet.appleToken)
      if (json) Ok(toJson(appleSeq(tokensSeq))) else Ok(toJson(tokensSeq))
    }
  }

  def url(protocol: String, host: String, callName: String, json: Boolean, amount: Int) = Action {
    val url = internet.url(protocol, host, callName, Map("" -> ""))
    if (amount == 1) {
      if (json) Ok(toJson(Map("url" -> url))) else Ok(url)
    } else {
      val urls = Seq.fill(amount)(internet.url(protocol, host, callName, Map("" -> "")))
      if (json) Ok(toJson(urlSeq(urls))) else Ok(toJson(urls))
    }
  }

  def ip(ipv6: Boolean, json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      val ip = if (ipv6) internet.ipv6 else internet.ip
      if (json) Ok(toJson(Map("ip" -> ip))) else Ok(ip)
    } else {
      val tokensSeq = Seq.fill(amount)(if (ipv6) internet.ipv6 else internet.ip )
      if (json) Ok(toJson(ipSeq(tokensSeq))) else Ok(toJson(tokensSeq))
    }
  }

  def macaddress(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("macaddress" -> internet.UUID))) else Ok(internet.macAddress)
    }else {
      val tokensSeq = Seq.fill(amount)(internet.macAddress)
      if (json) Ok(toJson(macAddressSeq(tokensSeq))) else Ok(toJson(tokensSeq))
    }
  }

  def uuid(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("uuid" -> internet.UUID))) else Ok(internet.UUID)
    }else {
      val uuidSeq = Seq.fill(amount)(internet.UUID)
      if (json) Ok(toJson(uuidsSeq(uuidSeq))) else Ok(toJson(uuidSeq))
    }
  }

  def color(format: String, greyscale: Boolean, json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("color" -> internet.color(format, greyscale)))) else Ok(internet.color(format, greyscale))
    }else {
      val colors = Seq.fill(amount)(internet.color(format, greyscale))
      if (json) Ok(toJson(colorsSeq(colors))) else Ok(toJson(colors))
    }
  }

  def twitter(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("twitter" -> internet.twitter))) else Ok(internet.twitter)
    }else {
      val twitters = Seq.fill(amount)(internet.twitter)
      if (json) Ok(toJson(twittersSeq(twitters))) else Ok(toJson(twitters))
    }
  }

  def hashtag(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("hashtag" -> internet.hashtag))) else Ok(internet.hashtag)
    }else {
      val hashtags = Seq.fill(amount)(internet.hashtag)
      if (json) Ok(toJson(hashTagsSeq(hashtags))) else Ok(toJson(hashtags))
    }
  }

  def googleanalytics(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("trackCode" -> internet.googleAnalyticsTrackCode))) else Ok(internet.googleAnalyticsTrackCode)
    }else {
      val trackingCodes = Seq.fill(amount)(internet.googleAnalyticsTrackCode)
      if (json) Ok(toJson(googleAnalyticsSeq(trackingCodes))) else Ok(toJson(trackingCodes))
    }
  }

  def facebookid(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("id" -> internet.facebookId))) else Ok(internet.facebookId)
    }else {
      val facebookIds = Seq.fill(amount)(internet.facebookId)
      if (json) Ok(toJson(facebookIdsSeq(facebookIds))) else Ok(toJson(facebookIds))
    }
  }



}

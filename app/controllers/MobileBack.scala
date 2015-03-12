package controllers

import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.mvc.{Action, Controller}

object MobileBack extends Controller{
  
  private val mobile = fabricator.Mobile()

  case class tokens(tokens: Seq[String])
  implicit def tokensWrite = Json.writes[tokens]

  def android(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("token" -> mobile.androidGsmId))) else Ok(mobile.androidGsmId)
    } else {
      val androidTokens = Seq.fill(amount)(mobile.androidGsmId)
      if (json) Ok(toJson(tokens(androidTokens))) else Ok(toJson(androidTokens))
    }
  }

  def apple(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("token" -> mobile.applePushToken))) else Ok(mobile.applePushToken)
    } else {
      val appleTokens = Seq.fill(amount)(mobile.applePushToken)
      if (json) Ok(toJson(tokens(appleTokens))) else Ok(toJson(appleTokens))
    }
  }

  def wp7(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("token" -> mobile.wp7_anid))) else Ok(mobile.wp7_anid)
    } else {
      val wp7Tokens = Seq.fill(amount)(mobile.wp7_anid)
      if (json) Ok(toJson(tokens(wp7Tokens))) else Ok(toJson(wp7Tokens))
    }
  }

  def wp8(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("token" -> mobile.wp8_anid2))) else Ok(mobile.wp8_anid2)
    } else {
      val wp8Tokens = Seq.fill(amount)(mobile.wp8_anid2)
      if (json) Ok(toJson(tokens(wp8Tokens))) else Ok(toJson(wp8Tokens))
    }
  }

  def blackberry(json: Boolean, amount: Int) = Action {
    if (amount == 1) {
      if (json) Ok(toJson(Map("token" -> mobile.blackBerryPin))) else Ok(mobile.blackBerryPin)
    } else {
      val blackBerryTokens = Seq.fill(amount)(mobile.blackBerryPin)
      if (json) Ok(toJson(tokens(blackBerryTokens))) else Ok(toJson(blackBerryTokens))
    }
  }
}

package controllers

import play.api.libs.json._
import play.api.libs.json.Json.toJson
import play.api.mvc.{Action, Controller}

object ContactBack extends Controller{

  private var contact = fabricator.Contact()
  
  private def checkLanguage(lang: String) = {if (!lang.equals(Language.US.toString)) contact = fabricator.Contact(lang)}
  
  case class Name(name: Map[String, String])
  case class Address(address: Map[String, String])
  case class Birthday(birthday: String)
  
  implicit def nameFormat = Json.format[Name]
  implicit def addressFormat = Json.format[Address]
  implicit def birthdayFormat = Json.format[Birthday]

  implicit val personMapFormat = new Format[Map[String, Object]] {

    def writes(map: Map[String, Object]): JsValue =
      Json.obj(
        "name" -> map("name").asInstanceOf[Map[String, String]],
        "address" -> map("address").asInstanceOf[Map[String, String]],
        "birthday" -> map("birthday").asInstanceOf[String],
        "email" -> map("email").asInstanceOf[String],
        "bsn" -> map("bsn").asInstanceOf[String],
        "religion" -> map("religion").asInstanceOf[String],
        "zodiac" -> map("zodiac").asInstanceOf[String],
        "height" -> map("height").asInstanceOf[String],
        "weight" -> map("weight").asInstanceOf[String],
        "blood_type" -> map("blood_type").asInstanceOf[String],
        "occupation" -> map("occupation").asInstanceOf[String]
      )

    def reads(jv: JsValue): JsResult[Map[String, Object]] =
    JsSuccess(Map(
      "name" -> (jv \ "name").as[Map[String, String]],
      "address" -> (jv \ "address").as[Map[String, String]],
      "birthday" -> (jv \ "birthday").as[String],
      "email" -> (jv \ "email").as[String],
      "bsn" -> (jv \ "bsn").as[String],
      "religion" -> (jv \ "religion").as[String],
      "zodiac" -> (jv \ "zodiac").as[String],
      "height" -> (jv \ "height").as[String],
      "weight" -> (jv \ "weight").as[String],
      "blood_type" -> (jv \ "blood_type").as[String],
      "occupation" -> (jv \ "occupation").as[String]))
  }
  
  def name(json: Boolean, lang: String) = Action {
    checkLanguage(lang)
    val name = Map("first_name" -> contact.firstName,
                   "last_name" -> contact.lastName,
                   "full_name" -> contact.fullName(false)) // TODO fix this method when new version of library will apper
    if (json) Ok(toJson(Name(name))) else Ok(toJson(contact.fullName(false)))
  }

  def birthday(age: Int, format: String,  json: Boolean, lang: String) = Action{
    checkLanguage(lang)
    if (json) Ok(toJson(Birthday(contact.birthday(age, format)))) else Ok(toJson(contact.birthday(age, format)))
  }

  def address(json: Boolean, lang: String) = Action {
    checkLanguage(lang)
    val address = Map("phone_number" -> contact.phoneNumber,
                      "street_name" -> contact.streetName,
                      "house_number" -> contact.houseNumber,
                      "appartment_number" -> contact.apartmentNumber,
                      "postcode" -> contact.postcode,
                      "address" -> contact.address,
                      "state" -> contact.state,
                      "state_short_code" -> contact.stateShortCode,
                      "company" -> contact.company
    )
    val addressString = String.format("%s %s", contact.address, contact.postcode)
    if (json) Ok(toJson(Address(address))) else Ok(toJson(addressString))
  }

  def person(age: Int, metric: Boolean, lang: String) = Action {
    checkLanguage(lang)
    val name = Map("first_name" -> contact.firstName,
      "last_name" -> contact.lastName,
      "full_name" -> contact.fullName(false))
    val address = Map("phone_number" -> contact.phoneNumber,
      "street_name" -> contact.streetName,
      "house_number" -> contact.houseNumber,
      "appartment_number" -> contact.apartmentNumber,
      "phone_number" -> contact.phoneNumber,
      "postcode" -> contact.postcode,
      "address" -> contact.address,
      "state" -> contact.state,
      "state_short_code" -> contact.stateShortCode,
      "company" -> contact.company
    )
    val person = Map("name" -> name,
                     "address" -> address,
                     "birthday" -> contact.birthday(age),
                     "email" -> contact.eMail,
                     "bsn" -> contact.bsn,
                     "religion" -> contact.religion,
                     "zodiac" -> contact.zodiac(contact.birthday(age)),
                     "height" -> contact.height(metric),
                     "weight" -> contact.weight,
                     "blood_type" -> contact.bloodType,
                     "occupation" -> contact.occupation
    )
    Ok(toJson(person))
  }

}

package controllers

import play.api.libs.json.Json.toJson
import play.api.libs.json._
import play.api.mvc.Action

object ContactBack extends ControllerBase{

  private var contact = fabricator.Contact()
  
  private def checkLanguage(lang: String) = {if (!lang.equals(Language.US.toString)) contact = fabricator.Contact(lang)}
  
  case class Name(name: Map[String, String])
  case class Address(address_details: Map[String, String])
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
    val firstName = contact.firstName
    val lastName = contact.lastName
    val fullName = String.format("%s %s", firstName, lastName)
    val name = Map("first_name" -> firstName,
                   "last_name" -> lastName,
                   "full_name" -> fullName)
    if (json) Ok(toJson(Name(name))) else Ok(toJson(fullName))
  }

  def birthday(age: Int, format: String,  json: Boolean, lang: String) = Action{
    checkLanguage(lang)
    val birthday: String = contact.birthday(age, matchFormat(format))
    if (json) Ok(toJson(Birthday(birthday))) else Ok(toJson(birthday))
  }

  def address(json: Boolean, lang: String) = Action {
    checkLanguage(lang)
    val address = Map("phone_number" -> contact.phoneNumber,
                      "street_name" -> contact.streetName,
                      "house_number" -> contact.houseNumber,
                      "apartment_number" -> contact.apartmentNumber,
                      "postcode" -> contact.postcode,
                      "address" -> contact.address,
                      "state" -> contact.state,
                      "state_short_code" -> contact.stateShortCode,
                      "company" -> contact.company
    )
    val addressString = String.format("%s %s", contact.address, contact.postcode)
    if (json) Ok(toJson(Address(address))) else Ok(toJson(addressString))
  }

  def person(age: Int, cm: Boolean, lang: String) = Action {
    checkLanguage(lang)
    val first_name = contact.firstName
    val last_name = contact.lastName
    val full_name = String.format("%s %s", first_name, last_name)
    val name = Map("first_name" -> first_name,
      "last_name" -> last_name,
      "full_name" -> full_name)
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
                     "height" -> contact.height(cm),
                     "weight" -> contact.weight(true),
                     "blood_type" -> contact.bloodType,
                     "occupation" -> contact.occupation
    )
    Ok(toJson(person))
  }

}

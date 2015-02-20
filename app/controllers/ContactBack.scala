package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object ContactBack extends Controller{
  
  private val contact = fabricator.Contact()
  
  def name(json: Boolean) = Action {
    val name = Map("first_name" -> contact.firstName,
                   "last_name" -> contact.lastName,   
                   "full_name" -> contact.fullName(false)) // TODO fix this method when new version of library will appear
    if (json) Ok(Json.toJson(name)) else Ok(Json.toJson(contact.fullName(false)))
  }
  
  def birthday(age: Int, format: String,  json: Boolean) = Action{
    if (json) Ok(Json.toJson(Map("value" -> contact.birthday(age, format)))) else Ok(Json.toJson(contact.birthday(age, format)))
  }
  
  def address(json: Boolean) = Action {
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
    if (json) Ok(Json.toJson(address)) else Ok(Json.toJson(addressString))
  }
  
  def person(age: Int, metric: Boolean) = Action {
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
    Ok(Json.toJson(address))
    
  }

}

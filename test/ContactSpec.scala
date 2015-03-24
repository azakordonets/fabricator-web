import org.joda.time.{Years, DateTime}
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.libs.json.JsObject
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

@RunWith(classOf[JUnitRunner])
class ContactSpec extends PlaySpecification {

  "REST API /api/v1/contact/name" should {

    "return random name in json format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/name"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("name").as[JsObject]
      val firstName = (responseResult \ "first_name").as[String]
      val lastName = (responseResult \ "last_name").as[String]
      val fullName = (responseResult \ "full_name").as[String]
      firstName must not(beNull[String])
      lastName must not(beNull[String])
      fullName must be equalTo String.format("%s %s", firstName, lastName)
    }

    "return random name in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/name?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).replaceAll("\"", "").split(" ")
      val firstName = responseResult(0)
      val lastName = responseResult(1)
      firstName must not(beNull[String])
      lastName must not(beNull[String])
    }
  }

  "REST API /api/v1/contact/birthday" should {

    "return birthday date of 25 years old person in json format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/birthday"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("birthday").as[String]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      val birthdate: DateTime = formatter.parseDateTime(responseResult)
      val today: DateTime = DateTime.now()
      val years: Years = Years.yearsBetween(birthdate, today)
      birthdate must not(beNull[DateTime])
      years.getYears must beEqualTo(25)
    }

    "return birthday date of 40 years old person in json format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/birthday?age=40"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("birthday").as[String]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      val birthdate: DateTime = formatter.parseDateTime(responseResult)
      val today: DateTime = DateTime.now()
      val years: Years = Years.yearsBetween(birthdate, today)
      birthdate must not(beNull[DateTime])
      years.getYears must beEqualTo(40)
    }

    "return birthday date of 25 years old person with custom date format in json format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/birthday?format=dd/MM/yyyy"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("birthday").as[String]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy")
      val birthdate: DateTime = formatter.parseDateTime(responseResult)
      val today: DateTime = DateTime.now()
      val years: Years = Years.yearsBetween(birthdate, today)
      birthdate must not(beNull[DateTime])
      years.getYears must beEqualTo(25)
    }

    "return random birthday in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/birthday?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).replaceAll("\"","")
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      val birthdate: DateTime = formatter.parseDateTime(responseResult)
      val today: DateTime = DateTime.now()
      val years: Years = Years.yearsBetween(birthdate, today)
      birthdate must not(beNull[DateTime])
      years.getYears must beEqualTo(25)
    }
  }

  "REST API /api/v1/contact/address" should {

    "return random address in json format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/address"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("address_details").as[JsObject]
      val houseNumber = (responseResult \ "house_number").as[String]
      val phoneNumber = (responseResult \ "phone_number").as[String]
      val state = (responseResult \ "state").as[String]
      val stateShortCode = (responseResult \ "state_short_code").as[String]
      val postcode = (responseResult \ "postcode").as[String]
      val streetName = (responseResult \ "street_name").as[String]
      val company = (responseResult \ "company").as[String]
      val address = (responseResult \ "address").as[String]
      val appartmentNumber = (responseResult \ "appartment_number").as[String]
      // assertion
      houseNumber must not(beNull[String])
      phoneNumber must not(beNull[String])
      state must not(beNull[String])
      stateShortCode must not(beNull[String])
      postcode must not(beNull[String])
      streetName must not(beNull[String])
      company must not(beNull[String])
      address must not(beNull[String])
      appartmentNumber must not(beNull[String])

    }

    "return random address in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/address?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).replaceAll("\"", "").split(" ")
      val firstName = responseResult(0)
      val lastName = responseResult(1)
      responseResult.foreach(_ must not(beNull[String]))
    }
  }

  "REST API /api/v1/contact/person" should {

    "return random person details in json format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/person"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("address").as[JsObject]
      // name part
      val nameSection = contentAsJson(result).\("name").as[JsObject]
      val first_name = (nameSection \ "first_name").as[String]
      val last_name = (nameSection \ "last_name").as[String]
      val full_name = (nameSection \ "full_name").as[String]
      //assertions
      first_name must not(beNull[String])
      last_name must not(beNull[String])
      full_name must not(beNull[String])
      full_name must be equalTo String.format("%s %s", first_name, last_name)
      //address part
      val addressSection = contentAsJson(result).\("address").as[JsObject]
      val houseNumber = (addressSection \ "house_number").as[String]
      val phoneNumber = (addressSection \ "phone_number").as[String]
      val state = (addressSection \ "state").as[String]
      val stateShortCode = (addressSection \ "state_short_code").as[String]
      val postcode = (addressSection \ "postcode").as[String]
      val streetName = (addressSection \ "street_name").as[String]
      val company = (addressSection \ "company").as[String]
      val address = (addressSection \ "address").as[String]
      val appartmentNumber = (addressSection \ "appartment_number").as[String]
      // assertion
      houseNumber must not(beNull[String])
      phoneNumber must not(beNull[String])
      state must not(beNull[String])
      stateShortCode must not(beNull[String])
      postcode must not(beNull[String])
      streetName must not(beNull[String])
      company must not(beNull[String])
      address must not(beNull[String])
      appartmentNumber must not(beNull[String])

      //other details part
      val birthday = contentAsJson(result).\("birthday").as[String]
      birthday must not(beNull[String])
      val email = contentAsJson(result).\("email").as[String]
      email must not(beNull[String])
      val bsn = contentAsJson(result).\("bsn").as[String]
      bsn must not(beNull[String])
      val religion = contentAsJson(result).\("religion").as[String]
      religion must not(beNull[String])
      val zodiac = contentAsJson(result).\("zodiac").as[String]
      zodiac must not(beNull[String])
      val height = contentAsJson(result).\("height").as[String]
      height must not(beNull[String])
      val weight = contentAsJson(result).\("weight").as[String]
      weight must not(beNull[String])
      val blood_type = contentAsJson(result).\("blood_type").as[String]
      blood_type must not(beNull[String])
      val occupation = contentAsJson(result).\("occupation").as[String]
      occupation must not(beNull[String])

    }

    "return random person details with custom age in json format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/person?age=40"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("address").as[JsObject]
      // name part
      val nameSection = contentAsJson(result).\("name").as[JsObject]
      val first_name = (nameSection \ "first_name").as[String]
      val last_name = (nameSection \ "last_name").as[String]
      val full_name = (nameSection \ "full_name").as[String]
      //assertions
      first_name must not(beNull[String])
      last_name must not(beNull[String])
      full_name must not(beNull[String])
      full_name must be equalTo String.format("%s %s", first_name, last_name)
      //address part
      val addressSection = contentAsJson(result).\("address").as[JsObject]
      val houseNumber = (addressSection \ "house_number").as[String]
      val phoneNumber = (addressSection \ "phone_number").as[String]
      val state = (addressSection \ "state").as[String]
      val stateShortCode = (addressSection \ "state_short_code").as[String]
      val postcode = (addressSection \ "postcode").as[String]
      val streetName = (addressSection \ "street_name").as[String]
      val company = (addressSection \ "company").as[String]
      val address = (addressSection \ "address").as[String]
      val appartmentNumber = (addressSection \ "appartment_number").as[String]
      // assertion
      houseNumber must not(beNull[String])
      phoneNumber must not(beNull[String])
      state must not(beNull[String])
      stateShortCode must not(beNull[String])
      postcode must not(beNull[String])
      streetName must not(beNull[String])
      company must not(beNull[String])
      address must not(beNull[String])
      appartmentNumber must not(beNull[String])

      //other details part
      val birthdayString = contentAsJson(result).\("birthday").as[String]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      val birthdate: DateTime = formatter.parseDateTime(birthdayString)
      val today: DateTime = DateTime.now()
      val years: Years = Years.yearsBetween(birthdate, today)
      birthdate must not(beNull[DateTime])
      years.getYears must beEqualTo(40)
      val email = contentAsJson(result).\("email").as[String]
      email must not(beNull[String])
      val bsn = contentAsJson(result).\("bsn").as[String]
      bsn must not(beNull[String])
      val religion = contentAsJson(result).\("religion").as[String]
      religion must not(beNull[String])
      val zodiac = contentAsJson(result).\("zodiac").as[String]
      zodiac must not(beNull[String])
      val height = contentAsJson(result).\("height").as[String]
      height must not(beNull[String])
      val weight = contentAsJson(result).\("weight").as[String]
      weight must not(beNull[String])
      val blood_type = contentAsJson(result).\("blood_type").as[String]
      blood_type must not(beNull[String])
      val occupation = contentAsJson(result).\("occupation").as[String]
      occupation must not(beNull[String])

    }

    "return random person details with not metric height in json format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/contact/person?metric=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("address").as[JsObject]
      val height = contentAsJson(result).\("height").as[String]
      height must not(beNull[String])
      val split = height.split(" ")
      split(0).toDouble must beBetween(1.50, 2.20)
      split(1) must be equalTo "cm"

    }

  }

}

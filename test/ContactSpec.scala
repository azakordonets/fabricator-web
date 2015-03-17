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

}

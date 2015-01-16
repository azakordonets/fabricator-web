import org.junit.runner._
import org.specs2.runner._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class CalendarSpec extends PlaySpecification {

  "REST API /api/v1/calendar/time" should {

    "return random time value in 24 hour format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/time"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").as[String]
      val hour = responseResult.split(":")(0).toInt
      val minutes = responseResult.split(":")(1).toInt
      hour must beBetween(0, 24)
      minutes must beBetween(0,60)
    }

    "return random time value in 12 hour format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/time?twentyFourHours=true"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").as[String]
      val hour = responseResult.split(":")(0).toInt
      val minutes = responseResult.split(":")(1).toInt
      hour must beBetween(0, 12)
      minutes must beBetween(0,60)
    }

    "return random time value in 24 hour format as text response" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/time?twentyFourHours=true&json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).replaceAll("\"","")
      val hour = responseResult.split(":")(0).toInt
      val minutes = responseResult.split(":")(1).toInt
      hour must beBetween(0, 24)
      minutes must beBetween(0,60)
    }
  }

}


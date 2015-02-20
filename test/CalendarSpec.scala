import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
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
      val responseResult = contentAsJson(result).\("time").as[String]
      val hour = responseResult.split(":")(0).toInt
      val minutes = responseResult.split(":")(1).toInt
      hour must beBetween(0, 24)
      minutes must beBetween(0, 60)
    }

    "return random time value in 12 hour format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/time?twentyFourHours=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("time").as[String]
      val hour = responseResult.split(":")(0).toInt
      val minutes = responseResult.split(":")(1).toInt
      hour must beBetween(0, 12)
      minutes must beBetween(0, 60)
    }

    "return random time value in 24 hour format as text response" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/time?twentyFourHours=true&json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).replaceAll("\"", "")
      val hour = responseResult.split(":")(0).toInt
      val minutes = responseResult.split(":")(1).toInt
      hour must beBetween(0, 24)
      minutes must beBetween(0, 60)
    }
  }

  "REST API /api/v1/calendar/date" should {
    "return random date in specified year" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/date?year=2000"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("date").as[String]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      val date = formatter.parseDateTime(responseResult)
      date.getYear must be equalTo 2000
      date.getMonthOfYear must beBetween(1, 12)
      date.getDayOfMonth must beBetween(1, 31)
    }

    "return random date in specified year and month" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/date?year=2000&month=2"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("date").as[String]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      val date = formatter.parseDateTime(responseResult)
      date.getYear must be equalTo 2000
      date.getMonthOfYear must be equalTo 2
      date.getDayOfMonth must beBetween(1, 31)
    }

    "return random date in specific day of random year and month" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/date?day=15"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("date").as[String]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      val date = formatter.parseDateTime(responseResult)
      val currentYear = new DateTime().getYear
      date.getYear must beBetween(1900, currentYear)
      date.getMonthOfYear must beBetween(1, 12)
      date.getDayOfMonth must be equalTo 15
    }

    "return random date in specific hour" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/date?hour=2&format=dd-MM-yyyy hh:mm"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("date").as[String]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy hh:mm")
      val date = formatter.parseDateTime(responseResult)
      val currentYear = new DateTime().getYear
      date.getYear must beBetween(1900, currentYear)
      date.getMonthOfYear must beBetween(1, 12)
      date.getDayOfMonth must be beBetween(1, 31)
      date.getHourOfDay must be equalTo 2
    }

    "return random date in specific minute" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/date?minute=20&format=dd-MM-yyyy hh:mm"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("date").as[String]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy hh:mm")
      val date = formatter.parseDateTime(responseResult)
      val currentYear = new DateTime().getYear
      date.getYear must beBetween(1900, currentYear)
      date.getMonthOfYear must beBetween(1, 12)
      date.getDayOfMonth must be beBetween(1, 31)
      date.getHourOfDay must be beBetween(0, 60)
      date.getMinuteOfHour must be equalTo 20
    }
  }

  "REST API /api/v1/calendar/dates" should {
    "return Sequence of random date in specified year" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/dates?year=2000"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("dates").as[Array[String]]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      for (response <- responseResult) {
        val date = formatter.parseDateTime(response)
        date.getYear must be equalTo 2000
        date.getMonthOfYear must beBetween(1, 12)
        date.getDayOfMonth must beBetween(1, 31)
      }
    }

    "return Sequence of random date in specified year and month" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/dates?year=2000&month=2"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("dates").as[Array[String]]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      responseResult must have size 100
      for (response <- responseResult) {
        val date = formatter.parseDateTime(response)
        date.getYear must be equalTo 2000
        date.getMonthOfYear must be equalTo 2
        date.getDayOfMonth must beBetween(1, 31)
      }
    }

    "return Sequence of random date in specific day of random year and month" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/dates?day=15"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("dates").as[Array[String]]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      responseResult must have size 100
      for (response <- responseResult) {
        val date = formatter.parseDateTime(response)
        val currentYear = new DateTime().getYear
        date.getYear must beBetween(1900, currentYear)
        date.getMonthOfYear must beBetween(1, 12)
        date.getDayOfMonth must be equalTo 15
      }
    }

    "return Sequence of random date in specific hour" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/dates?hour=2&format=dd-MM-yyyy hh:mm"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("dates").as[Array[String]]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy hh:mm")
      responseResult must have size 100
      for (response <- responseResult) {
        val date = formatter.parseDateTime(response)
        val currentYear = new DateTime().getYear
        date.getYear must beBetween(1900, currentYear)
        date.getMonthOfYear must beBetween(1, 12)
        date.getDayOfMonth must be beBetween(1, 31)
        date.getHourOfDay must be equalTo 2
      }
    }

    "return Sequence of random date in specific minute" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/dates?minute=20&format=dd-MM-yyyy hh:mm"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("dates").as[Array[String]]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy hh:mm")
      responseResult must have size 100
      for (response <- responseResult) {
        val date = formatter.parseDateTime(response)
        val currentYear = new DateTime().getYear
        date.getYear must beBetween(1900, currentYear)
        date.getMonthOfYear must beBetween(1, 12)
        date.getDayOfMonth must be beBetween(1, 31)
        date.getHourOfDay must be beBetween(0, 60)
        date.getMinuteOfHour must be equalTo 20
      }
    }

    "return Sequence of random date in specified year" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/dates?year=2000&amount=200"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("dates").as[Array[String]]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
      responseResult must have size 200
      for (response <- responseResult) {
        val date = formatter.parseDateTime(response)
        date.getYear must be equalTo 2000
        date.getMonthOfYear must beBetween(1, 12)
        date.getDayOfMonth must beBetween(1, 31)
      }
    }
  }

  "REST API /api/v1/calendar/datesRange" should {

    "return Sequence of random date in specified range with specified year step " in new WithApplication {
      val rangeCongif = "{\"start\":{\"year\":2001},\"end\":{\"year\":2010},\"step\":{\"year\":1}}"
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/datesRange?config=" + rangeCongif))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("range").as[Array[String]]
      val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy hh:mm")
      responseResult must have size 10
      for (response <- responseResult) {
        val date = formatter.parseDateTime(response)
        date.getYear must beBetween(2001, 2010)
        date.getMonthOfYear must beBetween(1, 12)
        date.getDayOfMonth must beBetween(1, 31)
      }
    }
  }

  "REST API /api/v1/calendar/relativeDate" should {
    
    "return - 2 years date from now" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/relativeDate?years=-2"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("relative_date").as[String]
      val expectedDate = new DateTime().minusYears(2).toString("dd-MM-yyyy")
      responseResult must be equalTo expectedDate
    }

    "return - 2 months date from now" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/relativeDate?months=-2"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("relative_date").as[String]
      val expectedDate = new DateTime().minusMonths(2).toString("dd-MM-yyyy")
      responseResult must be equalTo expectedDate
    }

    "return - 2 weeks date from now" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/relativeDate?weeks=-2"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("relative_date").as[String]
      val expectedDate = new DateTime().minusWeeks(2).toString("dd-MM-yyyy")
      responseResult must be equalTo expectedDate
    }

    "return - 2 days date from now" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/relativeDate?days=-2"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("relative_date").as[String]
      val expectedDate = new DateTime().minusDays(2).toString("dd-MM-yyyy")
      responseResult must be equalTo expectedDate
    }

    "return - 2 hours date from now" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/relativeDate?hours=-2&format=dd-MM-yyyy HH:mm"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("relative_date").as[String]
      val expectedDate = new DateTime().minusHours(2).toString("dd-MM-yyyy HH:mm")
      responseResult must be equalTo expectedDate
    }

    "return - 2 minutes date from now" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/relativeDate?minutes=-2&format=dd-MM-yyyy HH:mm"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("relative_date").as[String]
      val expectedDate = new DateTime().minusMinutes(2).toString("dd-MM-yyyy HH:mm")
      responseResult must be equalTo expectedDate
    }

    "return - 2 years, -2 months, -2 weeks, -2 days, -2hours, -2 minutes date from now" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/relativeDate?years=-2&months=-2&weeks=-2&days=-2&hours=-2&minutes=-2&format=dd-MM-yyyy HH:mm"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("relative_date").as[String]
      val expectedDate = new DateTime().minusYears(2).minusMonths(2).minusWeeks(2).minusDays(2).minusHours(2).minusMinutes(2).toString("dd-MM-yyyy HH:mm")
      responseResult must be equalTo expectedDate
    }

    "return +3 years, +3 months, +3 weeks, +3 days, +3hours, +3 minutes date from now" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/calendar/relativeDate?years=3&months=3&weeks=3&days=3&hours=3&minutes=3&format=dd-MM-yyyy HH:mm"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("relative_date").as[String]
      val expectedDate = new DateTime().plusYears(3).plusMonths(3).plusWeeks(3).plusDays(3).plusHours(3).plusMinutes(3).toString("dd-MM-yyyy HH:mm")
      responseResult must be equalTo expectedDate
    }
  }
}


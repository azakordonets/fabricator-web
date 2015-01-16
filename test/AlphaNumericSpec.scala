import java.nio.charset.Charset

import org.junit.runner._
import org.specs2.runner._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class AlphaNumericSpec extends PlaySpecification {

  "REST API /api/v1/alpha/integer" should {

    "return random int number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/integer"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Int] must be >= 0
    }

    "return random int number in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/integer?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsString(result).toInt must be > 0
    }


    "return random int number more then 100 with max parameter included" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/integer?min=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Int] must be >= 100
    }

    "return random int number in range [100, 3600] with max, min parameter included" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/integer?min=100&max=3600"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Int] must be >= 100
      contentAsJson(result).\("value").as[Int] must be <= 3600
    }

    "return Sequence of random int numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/integer?amount=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[Int]].get
      assert(responseResult.length == 100)
      assert(responseResult.isInstanceOf[Array[Int]])
    }

    "return Sequence of random int numbers in range [100, 200]" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/integer?amount=100&min=100&max=200"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[Int]].get
      assert(responseResult.isInstanceOf[Array[Int]])
      assert(responseResult.length == 100)
      for (number <- responseResult) assert(number >= 100 && number <= 200)
    }

    "return 400 response if min > max" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/integer?min=300&max=200"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain("Minimal number cannot be more then maximum")
    }

    "return 400 response if amount is < 1" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/integer?min=100&max=200&amount=0"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain("Amount should be more then 1")
    }

    "return 400 response in case of invalid argument" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/integer?min=abc&max=200&amount=0"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
    }
  }

  "REST API /api/v1/alpha/double" should {

    "return random Double number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Double] must be >= 0.0
    }

    "return random double number in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsString(result).toDouble must be > 0.0
    }

    "return random Double number more then 100 with max parameter included" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double?min=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Double] must be >= 100.0
    }

    "return random Double number in range [100, 3600] with max, min parameter included" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double?min=100&max=3600"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Double] must be >= 100.0
      contentAsJson(result).\("value").as[Double] must be <= 3600.0
    }

    "return random Double number with accuracy = 3" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double?accuracy=3&json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      assert(responseResult.split("\\.")(1).length <= 3)
    }

    "return Sequence of random Double numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double?amount=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[Double]].get
      responseResult must have size (100)
      assert(responseResult.isInstanceOf[Array[Double]])
    }

    "return Sequence of random Double numbers in range [100, 200]" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double?amount=100&min=100.0&max=200.0"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[Double]].get
      assert(responseResult.isInstanceOf[Array[Double]])
      responseResult must have size (100)
      for (number <- responseResult) {
        number must be >= 100.0  
        number must be <= 200.0
      }  
    }

    "return 400 response if min > max" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double?min=300.0&max=200.0"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain("Minimal number cannot be more then maximum")
    }

    "return 400 response if amount is < 1" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double?min=100.0&max=200.0&amount=0"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain("Amount should be more then 1")
    }

    "return 400 response in case of invalid argument" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/double?min=abc&max=200&amount=0"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
    }
  }

  "REST API /api/v1/alpha/string" should {

    "return random String with default length of 30 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/string"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (30)
    }

    "return random String with custom length of 2000 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/string?length=2000"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (2000)
    }

    "return random String with default length of 30 symbols as a String response" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/string?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).mkString
      responseResult must have size (32)
    }

    "return Sequence of random Strings with default length of 30 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/string?amount=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      responseResult should have size (100)
      for (string <- responseResult) string must have size (30)
    }

    "return Sequence of random Strings with custom length of 100 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/string?amount=100&length=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      responseResult must have size (100)
      for (string <- responseResult) string must have size (100)
    }

    "return 400 response if amount is < 1" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/string?amount=0"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain("Amount should be more then 1")
    }
  }

  "REST API /api/v1/alpha/hash" should {

    "return random Hash with default length of 40 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/hash"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (40)
    }

    "return random Hash with custom length of 2000 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/hash?length=2000"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (2000)
    }

    "return random Hash with default length of 40 symbols as a Hash response" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/hash?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).mkString
      responseResult must have size (42)
    }

    "return Sequence of random Strings with default length of 40 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/hash?amount=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      responseResult should have size (100)
      for (hash <- responseResult) hash must have size(40)
    }

    "return Sequence of random Strings with custom length of 100 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/hash?amount=100&length=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      responseResult must have size (100)
      for (hash <- responseResult) hash must have size (100)
    }

    "return 400 response if amount is < 1" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/hash?amount=0"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain("Amount should be more then 1")
    }

  }

  "REST API /api/v1/alpha/guid" should {

    "return random Guid with default length of 36 " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/guid"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (36)
      responseResult must beMatching("\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")
    }

    "return random Guid with custom length of 2000 " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/guid?length=2000"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (39)
    }

    "return random Guid with default length of 36 symbols as a text response" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/guid?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).mkString
      responseResult must have size (38)
    }

    "return Sequence of random Strings with default length of 36 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/guid?amount=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      responseResult should have size (100)
      for (guid <- responseResult) guid must have size (36)
    }

    "return Sequence of random Strings with custom length of 100 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/guid?amount=100&length=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      responseResult must have size (100)
      for (guid <- responseResult) {
        guid must have size (38)
        guid must beMatching("\\w{8}-\\w{4}-\\w{4,6}-\\w{4}-\\w{12}")
      }
    }

    "return 400 response if amount is < 1" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/guid?amount=0"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain("Amount should be more then 1")
    }
  }

  "REST API /api/v1/alpha/letterify" should {

    "return 400 if inputString is not specified or misspelled " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/letterify"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain ("For request 'GET /api/v1/alpha/letterify' [Missing parameter: inputString]")
    }

    "return random String with replaced ??? into random letters " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/letterify?inputString=????21312"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size ("????21312".length)
      responseResult must beMatching("\\w{4}\\d{5}")
    }

    "return random String with replaced ### into random numbers as plain text" in new WithApplication {
      val inputParameters = new String("???###".getBytes("UTF-8"), "UTF-8")
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/letterify?json=false&inputString=???ABC"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must have size ("???ABC".length + 2)
      responseResult must beMatching("\"\\w{6}\"")
    }
  }

  "REST API /api/v1/alpha/nummerify" should {

    "return random String with replaced ### into random numbers " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/numerify?inputString=###ABC"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size ("###ABC".length)
      responseResult must beMatching("\\d{3}\\w{3}")
    }

    "return random String with replaced ### into random numbers as plain text" in new WithApplication {
      val inputParameters = new String("???###".getBytes("UTF-8"), "UTF-8")
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/numerify?json=false&inputString=ABC###"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must have size ("ABC###".length + 2)
      responseResult must beMatching("\"\\w{3}\\d{3}\"")
    }
    
    "return 400 if inputString is not specified or misspelled " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/numerify"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain ("For request 'GET /api/v1/alpha/numerify' [Missing parameter: inputString]")
    }

  }

  "REST API /api/v1/alpha/botify" should {

    "return random String with replaced ### into random numbers " in new WithApplication {
      val inputParameters = new String("???###".getBytes("UTF-8"), "UTF-8")
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/botify?inputString="+inputParameters))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size ("???###".length)
      responseResult must beMatching("\\w{3}\\d{3}")
    }

    "return random String with replaced ### into random numbers as plain text" in new WithApplication {
      val inputParameters = new String("???###".getBytes("UTF-8"), "UTF-8")
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/botify?json=false&inputString="+inputParameters))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must have size ("???###".length + 2)
      responseResult must beMatching("\"\\w{3}\\d{3}\"")
    }
    
    "return 400 if inputString is not specified or misspelled " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/alpha/botify"))
      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      responseResult must contain ("For request 'GET /api/v1/alpha/botify' [Missing parameter: inputString]")
    }

    
  }

}


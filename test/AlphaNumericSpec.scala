import org.junit.runner._
import org.specs2.runner._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class AlphaNumericSpec extends PlaySpecification {

  "REST API /api/v1/integer" should {

    "return random int number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/integer"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Int] must be >= 0
    }

    "return random int number in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/integer?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsString(result).toInt must be > 0
    }


    "return random int number more then 100 with max parameter included" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/integer?min=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Int] must be >= 100
    }

    "return random int number in range [100, 500] with max, min parameter included" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/integer?min=100&max=500"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Int] must be >= 100
      contentAsJson(result).\("value").as[Int] must be <= 500
    }

    "return Sequence of random int numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/integer?amount=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[Int]].get
      assert(responseResult.length == 100)
      assert(responseResult.isInstanceOf[Array[Int]])
    }

    "return Sequence of random int numbers in range [100, 200]" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/integer?amount=100&min=100&max=200"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[Int]].get
      assert(responseResult.isInstanceOf[Array[Int]])
      assert(responseResult.length == 100)
      for (number <- responseResult) assert(number >= 100 && number <= 200)
    }
  }

  "REST API /api/v1/double" should {

    "return random Double number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/double"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Double] must be >= 0.0
    }

    "return random double number in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/double?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsString(result).toDouble must be > 0.0
    }

    "return random Double number more then 100 with max parameter included" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/double?min=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Double] must be >= 100.0
    }

    "return random Double number in range [100, 500] with max, min parameter included" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/double?min=100&max=500"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).\("value").as[Double] must be >= 100.0
      contentAsJson(result).\("value").as[Double] must be <= 500.0
    }

    "return random Double number with accuracy = 3" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/double?accuracy=3&json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result)
      assert(responseResult.split("\\.")(1).length <= 3)
    }

    "return Sequence of random Double numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/double?amount=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[Double]].get
      responseResult must have size (100)
      assert(responseResult.isInstanceOf[Array[Double]])
    }

    "return Sequence of random Double numbers in range [100, 200]" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/double?amount=100&min=100.0&max=200.0"))
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
  }

  "REST API /api/v1/string" should {

    "return random String with default length of 30 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/string"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (30)
    }

    "return random String with custom length of 2000 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/string?length=2000"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (2000)
    }

    "return random String with default length of 30 symbols as a String response" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/string?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).mkString
      responseResult must have size (32)
    }

    "return Sequence of random Strings with default length of 30 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/string?amount=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      assert(responseResult.length == 100)
      for (string <- responseResult) assert(string.length == 30)
    }

    "return Sequence of random Strings with custom length of 100 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/string?amount=100&length=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      responseResult must have size (100)
      for (string <- responseResult) string must have size (100)
    }


  }

  "REST API /api/v1/hash" should {

    "return random Hash with default length of 40 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/hash"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (40)
    }

    "return random Hash with custom length of 2000 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/hash?length=2000"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[String].get
      responseResult must have size (2000)
    }

    "return random Hash with default length of 40 symbols as a Hash response" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/hash?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsString(result).mkString
      responseResult must have size (42)
    }

    "return Sequence of random Strings with default length of 40 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/hash?amount=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      assert(responseResult.length == 100)
      for (hash <- responseResult) hash must have size(40)
    }

    "return Sequence of random Strings with custom length of 100 symbols" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/hash?amount=100&length=100"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val responseResult = contentAsJson(result).\("value").validate[Array[String]].get
      responseResult must have size (100)
      for (hash <- responseResult) hash must have size (100)
    }

  }

}


import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.test.{FakeRequest, WithApplication, PlaySpecification}

@RunWith(classOf[JUnitRunner])
class WordsSpec extends PlaySpecification {

  "REST API /api/v1/words/words" should {

    "return random words " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/words/words"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val wordsToken = contentAsJson(result).\("word").as[String]
      wordsToken must not(beNull[String])
    }

    "return random wordsToken in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/words/words?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val wordsToken = contentAsString(result)
      wordsToken must not(beNull[String])
    }

    "return array of random wordsTokens " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/words/words?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val wordsTokens = contentAsJson(result).\("words").as[Array[String]]
      wordsTokens must have size 10
      for (wordsToken <- wordsTokens) {
        wordsToken must not(beNull[String])
      }
    }
  }

  "REST API /api/v1/words/paragraph" should {

    "return random paragraph " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/words/paragraph"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val paragraphToken = contentAsJson(result).\("paragraph").as[String]
      paragraphToken must not(beNull[String])
      paragraphToken must have size 100
    }

    "return random paragraph " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/words/paragraph?length=200"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val paragraphToken = contentAsJson(result).\("paragraph").as[String]
      paragraphToken must not(beNull[String])
      paragraphToken must have size 200
    }

    "return random paragraphToken in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/words/paragraph?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val paragraphToken = contentAsString(result)
      paragraphToken must not(beNull[String])
    }
  }

  "REST API /api/v1/words/sentence" should {

    "return random sentence " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/words/sentence"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val sentenceToken = contentAsJson(result).\("sentence").as[String]
      sentenceToken must not(beNull[String])
      sentenceToken.size must be equalTo 100
    }

    "return random sentence " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/words/sentence?length=200"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val sentenceToken = contentAsJson(result).\("sentence").as[String]
      sentenceToken must not(beNull[String])
      sentenceToken.size must be equalTo 200
    }

    "return random sentenceToken in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/words/sentence?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val sentenceToken = contentAsString(result)
      sentenceToken must not(beNull[String])
      sentenceToken.size must be equalTo 100
    }
  }



}

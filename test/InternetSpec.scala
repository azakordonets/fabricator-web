import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.test.{FakeRequest, WithApplication, PlaySpecification}

/**
 * Created by Andrew Zakordonets on 26/03/15.
 */
@RunWith(classOf[JUnitRunner])
class InternetSpec extends PlaySpecification {

  "REST API /api/v1/internet/appleToken" should {

    "return random apple token " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/appleToken"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val token = contentAsJson(result).\("token").as[String]
      token must not(beNull[String])
      token must have size 64
    }

    "return random apple token in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/appleToken?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val token = contentAsString(result)
      token must not(beNull[String])
      token must have size 64
    }

    "return array of random apple tokens " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/appleToken?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val tokens = contentAsJson(result).\("tokens").as[Array[String]]
      tokens must have size 10
      for (token <- tokens) {
        token must not(beNull[String])
        token must have size 64
      }
    }

    "return random url " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/url"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val url = contentAsJson(result).\("url").as[String]
      url must not(beNull[String])
    }

    "return random url in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/url?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val url = contentAsString(result)
      url must not(beNull[String])
    }

    "return array of random urls " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/url?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val urls = contentAsJson(result).\("urls").as[Array[String]]
      urls must have size 10
      for (url <- urls) {
        url must not(beNull[String])
      }
    }

    "return random ip " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/ip"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val ip = contentAsJson(result).\("ip").as[String]
      ip must not(beNull[String])
    }

    "return random ip in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/ip?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val ip = contentAsString(result)
      ip must not(beNull[String])
    }

    "return array of random ips " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/ip?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val ips = contentAsJson(result).\("ips").as[Array[String]]
      ips must have size 10
      for (ip <- ips) {
        ip must not(beNull[String])
      }
    }

    "return random ip v6" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/ip?ipv6=true"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val ip = contentAsJson(result).\("ip").as[String]
      ip must not(beNull[String])
      ip must have size 39
    }
  }

}

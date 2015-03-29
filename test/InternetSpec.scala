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
  }

  "REST API /api/v1/internet/url" should {

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
  }

  "REST API /api/v1/internet/ip" should {

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

  "REST API /api/v1/internet/macaddress" should {

    "return random macaddress " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/macaddress"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val macaddress = contentAsJson(result).\("macaddress").as[String]
      macaddress must not(beNull[String])
      macaddress must have size 17
    }

    "return random macaddress in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/macaddress?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val macaddress = contentAsString(result)
      macaddress must not(beNull[String])
      macaddress must have size 17
    }

    "return array of random macaddresss " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/macaddress?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val macaddresss = contentAsJson(result).\("macAddresses").as[Array[String]]
      macaddresss must have size 10
      for (macaddress <- macaddresss) {
        macaddress must not(beNull[String])
        macaddress must have size 17
      }
    }
  }

  "REST API /api/v1/internet/uuid" should {

    "return random uuid " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/uuid"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val uuid = contentAsJson(result).\("uuid").as[String]
      uuid must not(beNull[String])
      uuid must have size 36
    }

    "return random uuid in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/uuid?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val uuid = contentAsString(result)
      uuid must not(beNull[String])
      uuid must have size 36
    }

    "return array of random uuids " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/uuid?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val uuids = contentAsJson(result).\("uuids").as[Array[String]]
      uuids must have size 10
      for (uuid <- uuids) {
        uuid must not(beNull[String])
        uuid must have size 36
      }
    }
  }

  "REST API /api/v1/internet/color" should {

    "return random hex color " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/color"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val color = contentAsJson(result).\("color").as[String]
      color must not(beNull[String])
      color.size must beLessThan(8)
    }

    "return random shorthex color " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/color?format=shorthex"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val color = contentAsJson(result).\("color").as[String]
      color must not(beNull[String])
      color.size must beLessThan(5)
    }

    "return random rgb color " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/color?format=rgb"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val color = contentAsJson(result).\("color").as[String]
      color must not(beNull[String])
      color.size must beLessThan(14)
    }

    "return random color in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/color?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val color = contentAsString(result)
      color must not(beNull[String])
      color.size must beLessThan(8)
    }

    "return array of random colors " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/color?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val colors = contentAsJson(result).\("colors").as[Array[String]]
      colors must have size 10
      for (color <- colors) {
        color must not(beNull[String])
        color.size must beLessThan(8)
      }
    }
  }

  "REST API /api/v1/internet/twitter" should {

    "return random twitter " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/twitter"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val twitter = contentAsJson(result).\("twitter").as[String]
      twitter must not(beNull[String])
      twitter must contain("@")
    }

    "return random twitter in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/twitter?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val twitter = contentAsString(result)
      twitter must not(beNull[String])
      twitter must contain("@")
    }

    "return array of random twitters " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/twitter?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val twitters = contentAsJson(result).\("twitters").as[Array[String]]
      twitters must have size 10
      for (twitter <- twitters) {
        twitter must not(beNull[String])
        twitter must contain("@")
      }
    }
  }

  "REST API /api/v1/internet/hashtag" should {

    "return random hashtag " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/hashtag"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val hashtag = contentAsJson(result).\("hashtag").as[String]
      hashtag must not(beNull[String])
      hashtag must contain("#")
    }

    "return random hashtag in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/hashtag?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val hashtag = contentAsString(result)
      hashtag must not(beNull[String])
      hashtag must contain("#")
    }

    "return array of random hashtags " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/hashtag?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val hashtags = contentAsJson(result).\("hashTags").as[Array[String]]
      hashtags must have size 10
      for (hashtag <- hashtags) {
        hashtag must not(beNull[String])
        hashtag must contain("#")
      }
    }
  }

  "REST API /api/v1/internet/googleAnalytic" should {

    "return random googleAnalytic " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/googleanalytics"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val googleAnalytic = contentAsJson(result).\("trackCode").as[String]
      googleAnalytic must not(beNull[String])
      googleAnalytic must have size 11
    }

    "return random googleAnalytic in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/googleanalytics?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val googleAnalytic = contentAsString(result)
      googleAnalytic must not(beNull[String])
      googleAnalytic must have size 11
    }

    "return array of random googleAnalytics " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/googleanalytics?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val googleAnalytics = contentAsJson(result).\("googleAnalytics").as[Array[String]]
      googleAnalytics must have size 10
      for (googleAnalytic <- googleAnalytics) {
        googleAnalytic must not(beNull[String])
        googleAnalytic must have size 11
      }
    }
  }

  "REST API /api/v1/internet/facebookId" should {

    "return random facebookId " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/facebookid"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val facebookId = contentAsJson(result).\("id").as[String]
      facebookId must not(beNull[String])
      facebookId must have size 16
    }

    "return random facebookId in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/facebookid?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val facebookId = contentAsString(result)
      facebookId must not(beNull[String])
      facebookId must have size 16
    }

    "return array of random facebookIds " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/internet/facebookid?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val facebookIds = contentAsJson(result).\("facebookIds").as[Array[String]]
      facebookIds must have size 10
      for (facebookId <- facebookIds) {
        facebookId must not(beNull[String])
        facebookId must have size 16
      }
    }
  }



}

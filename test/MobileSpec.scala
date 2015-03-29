import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.test.{FakeRequest, WithApplication, PlaySpecification}

@RunWith(classOf[JUnitRunner])
class MobileSpec extends PlaySpecification {

  "REST API /api/v1/mobile/android" should {

    "return random token " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/android"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val androidToken = contentAsJson(result).\("token").as[String]
      androidToken must not(beNull[String])
      androidToken must have size 183
    }

    "return random androidToken in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/android?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val androidToken = contentAsString(result)
      androidToken must not(beNull[String])
      androidToken must have size 183
    }

    "return array of random androidTokens " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/android?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val androidTokens = contentAsJson(result).\("tokens").as[Array[String]]
      androidTokens must have size 10
      for (androidToken <- androidTokens) {
        androidToken must not(beNull[String])
        androidToken must have size 183
      }
    }
  }

  "REST API /api/v1/mobile/applepush" should {

    "return random token " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/applepush"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val applepushToken = contentAsJson(result).\("token").as[String]
      applepushToken must not(beNull[String])
      applepushToken must have size 64
    }

    "return random applepushToken in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/applepush?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val applepushToken = contentAsString(result)
      applepushToken must not(beNull[String])
      applepushToken must have size 64
    }

    "return array of random applepushTokens " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/applepush?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val applepushTokens = contentAsJson(result).\("tokens").as[Array[String]]
      applepushTokens must have size 10
      for (applepushToken <- applepushTokens) {
        applepushToken must not(beNull[String])
        applepushToken must have size 64
      }
    }
  }

  "REST API /api/v1/mobile/wp7" should {

    "return random token " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/wp7"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val wp7Token = contentAsJson(result).\("token").as[String]
      wp7Token must not(beNull[String])
      wp7Token must have size 44
    }

    "return random wp7Token in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/wp7?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val wp7Token = contentAsString(result)
      wp7Token must not(beNull[String])
      wp7Token must have size 44
    }

    "return array of random wp7Tokens " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/wp7?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val wp7Tokens = contentAsJson(result).\("tokens").as[Array[String]]
      wp7Tokens must have size 10
      for (wp7Token <- wp7Tokens) {
        wp7Token must not(beNull[String])
        wp7Token must have size 44
      }
    }
  }

  "REST API /api/v1/mobile/wp8" should {

    "return random token " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/wp8"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val wp8Token = contentAsJson(result).\("token").as[String]
      wp8Token must not(beNull[String])
      wp8Token must have size 40
    }

    "return random wp8Token in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/wp8?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val wp8Token = contentAsString(result)
      wp8Token must not(beNull[String])
      wp8Token must have size 40
    }

    "return array of random wp8Tokens " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/wp8?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val wp8Tokens = contentAsJson(result).\("tokens").as[Array[String]]
      wp8Tokens must have size 10
      for (wp8Token <- wp8Tokens) {
        wp8Token must not(beNull[String])
        wp8Token must have size 40
      }
    }
  }

  "REST API /api/v1/mobile/blackberry" should {

    "return random token " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/blackberry"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val blackberryToken = contentAsJson(result).\("token").as[String]
      blackberryToken must not(beNull[String])
      blackberryToken must have size 8
    }

    "return random blackberryToken in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/blackberry?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val blackberryToken = contentAsString(result)
      blackberryToken must not(beNull[String])
      blackberryToken must have size 8
    }

    "return array of random blackberryTokens " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/mobile/blackberry?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val blackberryTokens = contentAsJson(result).\("tokens").as[Array[String]]
      blackberryTokens must have size 10
      for (blackberryToken <- blackberryTokens) {
        blackberryToken must not(beNull[String])
        blackberryToken must have size 8
      }
    }
  }

}

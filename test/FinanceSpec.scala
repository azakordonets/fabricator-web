import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

@RunWith(classOf[JUnitRunner])
class FinanceSpec extends PlaySpecification{

  "REST API /api/v1/finance/creditcard" should {

    "return random master card number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcard"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val master = contentAsJson(result).\("card_number").as[String]
      master must not(beNull[String])
      master must have size 16
    }

    "return random visa card number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcard?cardType=visa"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val visa = contentAsJson(result).\("card_number").as[String]
      visa must not(beNull[String])
      visa must have size 16
    }

    "return random visa card number in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcard?cardType=visa&json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val visa = contentAsString(result).replaceAll("\"", "")
      visa must not(beNull[String])
      visa must have size 16
    }

    "return random american express card number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcard?cardType=americanExpress"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val aexpress = contentAsJson(result).\("card_number").as[String]
      aexpress must not(beNull[String])
      aexpress must have size 16
    }

    "return random discover card number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcard?cardType=discover"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val discover = contentAsJson(result).\("card_number").as[String]
      discover must not(beNull[String])
      discover must have size 16
    }

    "return random diners card number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcard?cardType=diners"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val diners = contentAsJson(result).\("card_number").as[String]
      diners must not(beNull[String])
      diners must have size 16

    }

    "return random jcb card number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcard?cardType=jcb"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val jcb = contentAsJson(result).\("card_number").as[String]
      jcb must not(beNull[String])
      jcb must have size 16
    }

    "return random voyager card number" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcard?cardType=voyager"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val voyager = contentAsJson(result).\("card_number").as[String]
      voyager must not(beNull[String])
      voyager must have size 15
    }
  }

  "REST API /api/v1/finance/creditcards" should {

    "return array of random master card numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcards"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val masters = contentAsJson(result).\("cards").as[Array[String]]
      masters must have size 10
      for (master <- masters){
        master must not(beNull[String])
        master must have size 16
      }
    }

    "return array of random master card numbers with custom amoutn = 20" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcards?amount=20"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val masters = contentAsJson(result).\("cards").as[Array[String]]
      masters must have size 20
      for (master <- masters){
        master must not(beNull[String])
        master must have size 16
      }
    }

    "return array of random visa card numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcards?cardType=visa"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val visas = contentAsJson(result).\("cards").as[Array[String]]
      visas must have size 10
      for (visa <- visas) {
        visa must not(beNull[String])
        visa must have size 16
      }
    }

    "return array of random visa card numbers in plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcards?cardType=visa&json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val visas = contentAsString(result).replaceAll("\\[","").replaceAll("\\]","").split(",")
      visas must have size 10
      for (visa <- visas){
        visa must not(beNull[String])
        visa must have size 18
      }
    }

    "return array of random american express card numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcards?cardType=americanExpress"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val aexpresses = contentAsJson(result).\("cards").as[Array[String]]
      aexpresses must have size 10
      for (aexpress <- aexpresses){
        aexpress must not(beNull[String])
        aexpress must have size 16
      }
    }

    "return array of random discover card numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcards?cardType=discover"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val discovers = contentAsJson(result).\("cards").as[Array[String]]
      discovers must have size 10
      for (discover <- discovers){
        discover must not(beNull[String])
        discover must have size 16
      }
    }

    "return array of random diners card numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcards?cardType=diners"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val diners = contentAsJson(result).\("cards").as[Array[String]]
      diners must have size 10
      for (diner <- diners){
        diner must not(beNull[String])
        diner must have size 16
      }

    }

    "return array of random jcb card numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcards?cardType=jcb"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val jcbs = contentAsJson(result).\("cards").as[Array[String]]
      jcbs must have size 10
      for (jcb <- jcbs){
        jcb must not(beNull[String])
        jcb must have size 16
      }
    }

    "return array of random voyager card numbers" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/finance/creditcards?cardType=voyager"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val voyagers = contentAsJson(result).\("cards").as[Array[String]]
      voyagers must have size 10
      for (voyager <- voyagers){
        voyager must not(beNull[String])
        voyager must have size 15
      }
    }
  }
}

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.test.{FakeRequest, WithApplication, PlaySpecification}

@RunWith(classOf[JUnitRunner])
class LocationSpec extends PlaySpecification {

  "REST API /api/v1/location/altitude" should {

    "return random altitude" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/altitude"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val altitude = contentAsJson(result).\("altitude").as[String].toDouble
      altitude should beLessThan(8848.0)
    }

    "return random altitude as plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/altitude?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val altitude = contentAsString(result).toDouble
      altitude should beLessThan(8848.0)
    }

    "return random altitude that is less then 1000" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/altitude?max=1000"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val altitude = contentAsJson(result).\("altitude").as[String].toDouble
      altitude should beLessThan(1000.0)
    }

    "return array of random altitude" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/altitude?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val altitudes = contentAsJson(result).\("altitudes").as[Array[String]]
      altitudes should have size 10
      for (altitude <- altitudes) altitude.toDouble should beLessThan(8848.0)
    }
  }

  "REST API /api/v1/location/depth" should {

    "return random depth" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/depth"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val depth = contentAsJson(result).\("depth").as[String].toDouble
      depth should beLessThan(8848.0)
    }

    "return random depth as plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/depth?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val depth = contentAsString(result).toDouble
      depth should beLessThan(8848.0)
    }

    "return random depth that is less then 1000" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/depth?min=-1000"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val depth = contentAsJson(result).\("depth").as[String].toDouble
      depth should beGreaterThan(-1000.0)
      depth should beLessThan(0.0)
    }

    "return array of random depth" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/depth?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val depths = contentAsJson(result).\("depths").as[Array[String]]
      depths should have size 10
      for (depth <- depths) depth.toDouble should beLessThan(8848.0)
    }
  }

  "REST API /api/v1/location/coordinates" should {

    "return random coordinates" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/coordinates"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val coordinates = contentAsJson(result).\("coordinates").as[String]
      val latitude = coordinates.split(",")(0).toDouble
      val longitude = coordinates.split(",")(1).toDouble
      latitude should beLessThan(90.0)
      latitude should beGreaterThan(-90.0)
      longitude should beLessThan(180.0)
      longitude should beGreaterThan(-180.0)
    }

    "return random coordinates as plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/coordinates?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val coordinates = contentAsString(result)
      val latitude = coordinates.split(",")(0).toDouble
      val longitude = coordinates.split(",")(1).toDouble
      latitude should beLessThan(90.0)
      latitude should beGreaterThan(-90.0)
      longitude should beLessThan(180.0)
      longitude should beGreaterThan(-180.0)
    }

    "return random coordinates with accuracy 2" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/coordinates?accuracy=2"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val coordinates = contentAsJson(result).\("coordinates").as[String]
      val latitude = coordinates.split(",")(0).toDouble
      val longitude = coordinates.split(",")(1).toDouble
      println(latitude.toString)
      println(longitude.toString)
      latitude.toString.split("\\.")(1).size must beLessThan(3)
      longitude.toString.split("\\.")(1).size  must beLessThan(3)
    }

    "return array of random coordinates" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/coordinates?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val coordinatess = contentAsJson(result).\("coordinates").as[Array[String]]
      coordinatess should have size 10
      for (coordinates <- coordinatess) {
        val latitude = coordinates.split(",")(0).toDouble
        val longitude = coordinates.split(",")(1).toDouble
        latitude should beLessThan(90.0)
        latitude should beGreaterThan(-90.0)
        longitude should beLessThan(180.0)
        longitude should beGreaterThan(-180.0)
      }
    }
  }

  "REST API /api/v1/location/latitude" should {

    "return random latitude" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/latitude"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val latitude = contentAsJson(result).\("latitude").as[String].toDouble
      latitude should beLessThan(90.0)
      latitude should beGreaterThan(-90.0)
    }

    "return random latitude as plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/latitude?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val latitude = contentAsString(result).toDouble
      latitude should beLessThan(90.0)
      latitude should beGreaterThan(-90.0)
    }

    "return random latitude that is less then 1000" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/latitude?accuracy=2"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val latitude = contentAsJson(result).\("latitude").as[String].toDouble
      latitude should beLessThan(90.0)
      latitude should beGreaterThan(-90.0)
      latitude.toString.split("\\.")(1).size  must beLessThan(3)
    }

    "return array of random latitude" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/latitude?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val latitudes = contentAsJson(result).\("latitudes").as[Array[String]]
      latitudes should have size 10
      for (latitude <- latitudes) {
        latitude.toDouble should beLessThan(90.0)
        latitude.toDouble should beGreaterThan(-90.0)
      }
    }
  }

  "REST API /api/v1/location/longitude" should {

    "return random longitude" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/longitude"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val longitude = contentAsJson(result).\("longitude").as[String].toDouble
      longitude should beLessThan(180.0)
      longitude should beGreaterThan(-180.0)
    }

    "return random longitude as plain text" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/longitude?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val longitude = contentAsString(result).toDouble
      longitude should beLessThan(180.0)
      longitude should beGreaterThan(-180.0)
    }

    "return random longitude that is less then 1000" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/longitude?accuracy=2"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val longitude = contentAsJson(result).\("longitude").as[String].toDouble
      longitude should beLessThan(180.0)
      longitude should beGreaterThan(-180.0)
      longitude.toString.split("\\.")(1).size must beLessThan(3)
    }

    "return array of random longitude" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/longitude?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val longitudes = contentAsJson(result).\("longitudes").as[Array[String]]
      longitudes should have size 10
      for (longitude <- longitudes) {
        longitude.toDouble should beLessThan(180.0)
        longitude.toDouble should beGreaterThan(-180.0)
      }
    }
  }

  "REST API /api/v1/location/geohash" should {

    "return random  geohash " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/geohash"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val geohash = contentAsJson(result).\("geohash").as[String]
      geohash must not(beNull[String])
      geohash must have size 12
    }

    "return random  geohash in plain format" in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/geohash?json=false"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      val geohash = contentAsString(result)
      geohash must not(beNull[String])
      geohash must have size 12
    }

    "return array of random  geohashs " in new WithApplication {
      val Some(result) = route(FakeRequest(GET, "/api/v1/location/geohash?amount=10"))
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      charset(result) must beSome("utf-8")
      val geohashs = contentAsJson(result).\("geohashes").as[Array[String]]
      geohashs must have size 10
      for (geohash <- geohashs) {
        geohash must not(beNull[String])
        geohash must have size 12
      }
    }
  }

}

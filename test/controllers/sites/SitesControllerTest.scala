package controllers.sites

import domain.sites.Site
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._


/**
  * Created by hashcode on 2016/09/07.
  */

class SitesControllerTest extends PlaySpec with OneAppPerTest {
  val site = Site(
    "SITEID",
    "TEST SITE",
    "www.test.com")

  "Routes" should {


    "SiteController" should {

      "Create Site Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/sites/create")
          .withJsonBody(Json.toJson(site)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Site From Controller" in {
        val request = route(app, FakeRequest(GET, "/sites/get/SITEID")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get Sites From Controller" in {
        val request = route(app, FakeRequest(GET, "/sites/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }
}

package controllers.sites

import domain.sites.Administrators
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Quest on 2016/12/01.
  */
class AdministratorsControllerTest extends PlaySpec with OneAppPerTest{

  val administrators = Administrators("101","admin1@hashcode.com")

  "Routes" should {


    "AdministratorsController" should {

      "Create Administrator Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/Administrator/create")
          .withJsonBody(Json.toJson(administrators)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Administrator From Controller" in {
        val request = route(app, FakeRequest(GET, "/Administrator/get/siteId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get Administrators From Controller" in {
        val request = route(app, FakeRequest(GET, "/Administrator/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Delete Administrators From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/Administrator/del/siteId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}

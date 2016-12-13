package controllers.sites

import domain.sites.AdminStatus
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Quest on 2016/12/01.
  */
class AdminStatusControllerTest extends PlaySpec with OneAppPerTest{

  val adminStatus = AdminStatus("1","admin1")

  "Routes" should {


    "AdminStatusController" should {

      "Create AdminStatus Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/AdminStatus/create")
          .withJsonBody(Json.toJson(adminStatus)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get AdminStatus From Controller" in {
        val request = route(app, FakeRequest(GET, "/AdminStatus/get/AdminStatusId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get AdminStatuses From Controller" in {
        val request = route(app, FakeRequest(GET, "/AdminStatus/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Delete AdminStatus Controller" in {
        val request = route(app, FakeRequest(DELETE, "/AdminStatus/del/AdminStatusId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}

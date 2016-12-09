package controllers.util

import domain.util.Roles
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Quest on 2016/12/09.
  */
class RolesControllerTest extends PlaySpec with OneAppPerTest{

  val role = new Roles("ID","ROLENAME")

  "Routes" should {

    "RolesController" should {

      "Create Role Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/Roles/create")
          .withJsonBody(Json.toJson(role)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Role From Controller" in {
        val request = route(app, FakeRequest(GET, "/Roles/get/id")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get Roles From Controller" in {
        val request = route(app, FakeRequest(GET, "/Roles/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

    }
  }
}

package controllers.util

import domain.util.Token
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Quest on 2016/12/09.
  */
class TokenControllerTest extends PlaySpec with OneAppPerTest{


  val token = new Token("ID","TOKENVALUE")

  "Routes" should {


    "TokenController" should {

      "Save TokenController Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/Token/save")
          .withJsonBody(Json.toJson(token)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Create TokenController Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/Token/create")
          .withJsonBody(Json.toJson(token)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get whether Expired Token From Controller" in {
        val request = route(app, FakeRequest(GET, "/Token/get/hasTokenExpired")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get RevokedToken From Controller" in {
        val request = route(app, FakeRequest(GET, "/Token/get/revokeToken")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get ValidateToken From Controller" in {
        val request = route(app, FakeRequest(GET, "/Token/get/validToken")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}

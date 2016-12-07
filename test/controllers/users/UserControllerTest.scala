package controllers.users

import domain.users.User
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Created by Rosie on 2016/12/02.
 */
class UserControllerTest extends PlaySpec with OneAppPerTest {
  val user = User(
    "SITEID",
    "email",
    "screenName",Some("firstname"), Some("lastName"), "password")

  "Routes" should {


    "UserController" should {

      "Create User Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/user/create")
          .withJsonBody(Json.toJson(user)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get User From Controller" in {
        val request = route(app, FakeRequest(GET, "/user/get/email")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get User From Controller" in {
        val request = route(app, FakeRequest(GET, "/user/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}

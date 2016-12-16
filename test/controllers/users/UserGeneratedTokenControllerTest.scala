package controllers.users

import domain.users.UserGeneratedToken
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Created by Rosie on 2016/12/15.
 */
class UserGeneratedTokenControllerTest extends PlaySpec with OneAppPerTest {
  val userGeneratedToken = UserGeneratedToken(
    "101","available","online","github")

  "Routes" should {


    "UserGeneratedTokenController" should {

      "Create User Generated Token Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/userGeneratedToken/create")
          .withJsonBody(Json.toJson(userGeneratedToken)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get User Generated Token From Controller" in {
        val request = route(app, FakeRequest(GET, "/userGeneratedToken/get/token")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get all User Generated Token From Controller" in {
        val request = route(app, FakeRequest(GET, "/userGeneratedToken/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}

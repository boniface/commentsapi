package controllers.users

import domain.users.UserStatus
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Created by Rosie on 2016/12/15.
 */
class UserItemStatusControllerTest extends PlaySpec with OneAppPerTest {
  val userStatus = UserStatus(
    "github", "102", "offline",new DateTime(2016,5,12))

  "Routes" should {


    "UserStatusController" should {

      "Create UserStatus Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/userStatus/create")
          .withJsonBody(Json.toJson(userStatus)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get UserStatus From Controller" in {
        val request = route(app, FakeRequest(GET, "/userStatus/get/user")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get UserStatus From Controller" in {
        val request = route(app, FakeRequest(GET, "/userStatus/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}

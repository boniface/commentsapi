package controllers.users

import domain.users.UserSession
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Created by Rosie on 2016/12/15.
 */
class UserSessionControllerTest extends PlaySpec with OneAppPerTest {
  val userSession = UserSession(
    "myemail", "s10", new DateTime(),"10.4.2.6","fire5","offline","102")

  "Routes" should {


    "UserSessionController" should {

      "Create UserSession Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/userSession/create")
          .withJsonBody(Json.toJson(userSession)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("userSession/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get UserSession From Controller" in {
        val request = route(app, FakeRequest(GET, "/userSession/get/sessionId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get all UserSession From Controller" in {
        val request = route(app, FakeRequest(GET, "/userSession/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}

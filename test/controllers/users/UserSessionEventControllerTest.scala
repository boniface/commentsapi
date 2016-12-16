package controllers.users

import domain.users.UserSessionEvent
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Created by Rosie on 2016/12/15.
 */
class UserSessionEventControllerTest extends PlaySpec with OneAppPerTest {
  val sessionEvent = UserSessionEvent(
    "s1", "101", new DateTime(),"loggin")

  "Routes" should {


    "UserSessionEventController" should {

      "Create UserSessionEvent Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/userSessionEvent/create")
          .withJsonBody(Json.toJson(sessionEvent)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get UserSessionEvent From Controller" in {
        val request = route(app, FakeRequest(GET, "/userSessionEvent/get/id")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get all UserSessionEvent From Controller" in {
        val request = route(app, FakeRequest(GET, "/userSessionEvent/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

    }
  }

}

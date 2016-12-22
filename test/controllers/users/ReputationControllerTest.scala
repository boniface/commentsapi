package controllers.users

import domain.users.Reputation
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Created by Rosie on 2016/12/02.
 */
class ReputationControllerTest extends PlaySpec with OneAppPerTest {
//  val reputation = Reputation(
//    "EMAILID",
//    new DateTime(2016,11,27,6,0,0,0),
//   2)
//
//  "Routes" should {
//
//
//    "ReputationController" should {
//
//      "Create User Reputation Object in Controller" in {
//        val request =  route(app, FakeRequest(POST, "/reputation/create")
//          .withJsonBody(Json.toJson(reputation)))
//          .get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Content is ", contentAsString(request))
//      }
//
//      "Get User Reputaion From Controller" in {
//        val request = route(app, FakeRequest(GET, "/reputation/get/EMAILID")
//        ).get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Output", contentAsJson(request))
//      }
//
//      "Get User Reputation From Controller" in {
//        val request = route(app, FakeRequest(GET, "/reputation/get/all")
//        ).get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Output", contentAsJson(request))
//      }
//
//
//    }
//  }

}

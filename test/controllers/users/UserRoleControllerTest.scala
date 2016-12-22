package controllers.users

import domain.users.UserRole
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Created by Rosie on 2016/12/07.
 */
class UserRoleControllerTest extends PlaySpec with OneAppPerTest {
//  val role = UserRole(
//    "user@mail.fr",
//    "admin",
//    new DateTime(2016,5,2))
//
//  "Routes" should {
//
//
//    "UserRole Controller" should {
//
//      "Create User Role Object in Controller" in {
//        val request =  route(app, FakeRequest(POST, "/userRole/create")
//          .withJsonBody(Json.toJson(role)))
//          .get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Content is ", contentAsString(request))
//      }
//
//      "Get User Role From Controller" in {
//        val request = route(app, FakeRequest(GET, "/userRole/get/emailId")
//        ).get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Output", contentAsJson(request))
//      }
//
//      "Get User Role From Controller" in {
//        val request = route(app, FakeRequest(GET, "/userRole/get/all")
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

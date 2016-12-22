package controllers.comments
import domain.comments.Abuse
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Bonga on 12/2/2016.
  */
class AbuseControllerTest extends PlaySpec with OneAppPerTest{

//
//  val abuse = Abuse("100", "200", "300", "400", "wwew", "bmabulu@webmail.co.za",DateTime)
//
//  "Routes" should {
//
//
//    "Comment" should {
//
//      "Create Abuse Object in Controller" in {
//        val request =  route(app, FakeRequest(POST, "/Abuse/create")
//          .withJsonBody(Json.toJson(Abuse)))
//          .get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Content is ", contentAsString(request))
//      }
//
//      "Get Abuse From Controller" in {
//        val request = route(app, FakeRequest(GET, "/Abuse/get/date")
//        ).get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Output", contentAsJson(request))
//      }
//
//      "Get Abuse From Controller" in {
//        val request = route(app, FakeRequest(GET, "/Abuse/get/all")
//        ).get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Output", contentAsJson(request))
//      }
//
//      "Delete Abuse From Controller" in {
//        val request = route(app, FakeRequest(DELETE, "/Abuse/del/all")
//        ).get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Output", contentAsJson(request))
//      }
//    }
//  }

}

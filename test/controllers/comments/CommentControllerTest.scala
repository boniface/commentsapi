package controllers.comments
import domain.comments.Comment
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Bonga on 12/2/2016.
  */
class CommentControllerTest extends PlaySpec with OneAppPerTest{

//
//  val comment = Comment("100", "200", "300", "400", "wwew", "bmabulu@webmail.co.za",DateTime)
//
//  "Routes" should {
//
//
//    "Comment" should {
//
//      "Create Comment Object in Controller" in {
//        val request =  route(app, FakeRequest(POST, "/Comment/create")
//          .withJsonBody(Json.toJson(Comment)))
//          .get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Content is ", contentAsString(request))
//      }
//
//      "Get Comment From Controller" in {
//        val request = route(app, FakeRequest(GET, "/Comment/get/date")
//        ).get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Output", contentAsJson(request))
//      }
//
//      "Get Comment From Controller" in {
//        val request = route(app, FakeRequest(GET, "/Comment/get/all")
//        ).get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Output", contentAsJson(request))
//      }
//
//      "Delete Comment From Controller" in {
//        val request = route(app, FakeRequest(DELETE, "/Comment/del/all")
//        ).get
//        status(request) mustBe OK
//        contentType(request) mustBe Some("application/json")
//        println(" The Output", contentAsJson(request))
//      }
//    }
//  }

}


package controllers.comments
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Bonga on 12/2/2016.
  */
class AbuseControllerTest extends PlaySpec with OneAppPerTest{


  val Subject = Subject("100", "200", "300", "400", "wwew", "bmabulu@webmail.co.za",DateTime)

  "Routes" should {


    "Subject" should {

      "Create Subject Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/Subject/create")
          .withJsonBody(Json.toJson(Subject)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Subject From Controller" in {
        val request = route(app, FakeRequest(GET, "/Subject/get/date")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get Subject From Controller" in {
        val request = route(app, FakeRequest(GET, "/Subject/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Delete Subject From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/Subject/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }

}

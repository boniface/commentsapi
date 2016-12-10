package controllers.util

import java.util.Date

import domain.util.Mail
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Quest on 2016/12/09.
  */
class MailControllerTest extends PlaySpec with OneAppPerTest{


  val mail = new Mail("SITEID","ID","KEY","VALUE","HOST","PORT","STATE",new Date)

  "Routes" should {


    "MailController" should {

      "Create Mail Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/Mail/create")
          .withJsonBody(Json.toJson(mail)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get getMailer From Controller" in {
        val request = route(app, FakeRequest(GET, "/Mail/get/orgId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get GetMail From Controller" in {
        val request = route(app, FakeRequest(GET, "/Mail/get/all/orgId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Delete getMail From Controller" in {
        val request = route(app, FakeRequest(GET, "/Mail/get/mail")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}

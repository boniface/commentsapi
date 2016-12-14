package controllers.votes

import domain.votes.VoterRegister
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._
/**
  * Created by fatimam on 07/12/2016.
  */
class VoterRegisterControllerTest extends PlaySpec with OneAppPerTest
{
  val voterRegister = VoterRegister("1","1","124.222.252")

  "Routes" should {
    "Comment" should {

      "Create VoterRegister Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/VoterRegister/create")
          .withJsonBody(Json.toJson(voterRegister)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get VoterRegister From Controller" in {
        val request = route(app, FakeRequest(GET, "/VoterRegister/get/date")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get VoterRegister From Controller" in {
        val request = route(app, FakeRequest(GET, "/VoterRegister/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}

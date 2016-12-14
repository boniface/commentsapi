package controllers.votes

import domain.votes.VoteUp
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by fatimam on 07/12/2016.
  */
class VoteUpControllerTest extends PlaySpec with OneAppPerTest
{
  val voteUp = VoteUp("1","1","124.222.252",1)

  "Routes" should {
    "Comment" should {

      "Create VoteUp Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/VoteUp/create")
          .withJsonBody(Json.toJson(voteUp)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get VoteUp From Controller" in {
        val request = route(app, FakeRequest(GET, "/VoteUp/get/date")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get VoteUp From Controller" in {
        val request = route(app, FakeRequest(GET, "/VoteUp/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}

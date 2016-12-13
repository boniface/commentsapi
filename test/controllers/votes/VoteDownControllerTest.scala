package controllers.votes

import domain.votes.VoteDown
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by fatimam on 07/12/2016.
  */
class VoteDownControllerTest extends PlaySpec with OneAppPerTest
{

  val voteDown = VoteDown("1","1","124.222.222",1)

  "Routes" should {


    "Comment" should {

      "Create VoteDown Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/VoteDown/create")
          .withJsonBody(Json.toJson(voteDown)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get VoteDown From Controller" in {
        val request = route(app, FakeRequest(GET, "/VoteDown/get/date")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get VoteDown From Controller" in {
        val request = route(app, FakeRequest(GET, "/VoteDown/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

    }
  }

}

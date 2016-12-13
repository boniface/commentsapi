package controllers.util

import domain.util.Keys
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Quest on 2016/12/09.
  */
class KeysControllerTest extends PlaySpec with OneAppPerTest{


  val keys = new Keys("ID","VALUE")

  "Routes" should {

    "KeysController" should {

      "Create Keys Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/Keys/create")
          .withJsonBody(Json.toJson(keys)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get All Keys From Controller" in {
        val request = route(app, FakeRequest(GET, "/Keys/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get Keys From Controller" in {
        val request = route(app, FakeRequest(GET, "/Keys/get/id")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

    }
  }
}

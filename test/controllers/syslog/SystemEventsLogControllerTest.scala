package controllers.syslog

import domain.syslog.SystemLogEvents
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Quest on 2016/12/01.
  */
class SystemEventsLogControllerTest extends PlaySpec with OneAppPerTest{

  val systemLogEvents = SystemLogEvents(".mil","DOD","Http Error","HttpError","Error 404",new DateTime())

  "Routes" should {


    "SystemLogEventController" should {

      "Create SystemLogEvent Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/SystemLogEvent/create")
          .withJsonBody(Json.toJson(systemLogEvents)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get SystemLogEvent by Date From Controller" in {
        val request = route(app, FakeRequest(GET, "/SystemLogEvent/get/date")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get SystemLogEvent by Id From Controller" in {
        val request = route(app, FakeRequest(GET, "/SystemLogEvent/get/id")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Delete SystemLogEvent by Id From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/SystemLogEvent/del/id")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get SystemLogEvent all From Controller" in {
        val request = route(app, FakeRequest(GET, "/SystemLogEvent/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}

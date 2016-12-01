package controllers.sites

import domain.sites.AdministratorHistory
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Quest on 2016/12/01.
  */
class AdministratorHistoryControllerTest extends PlaySpec with OneAppPerTest{


  val administratorHistory = AdministratorHistory("101","admin1@hashcode.com","1",new DateTime())

  "Routes" should {


    "AdministratorHistoryController" should {

      "Create AdministratorHistory Object in Controller" in {
        val request =  route(app, FakeRequest(POST, "/AdministratorHistory/create")
          .withJsonBody(Json.toJson(administratorHistory)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get AdministratorHistory From Controller" in {
        val request = route(app, FakeRequest(GET, "/AdministratorHistory/get/date")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Get AdministratorHistory From Controller" in {
        val request = route(app, FakeRequest(GET, "/AdministratorHistory/get/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }

      "Delete AdministratorHistory From Controller" in {
        val request = route(app, FakeRequest(DELETE, "/Administrator/del/all")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}

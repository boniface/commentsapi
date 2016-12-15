package controllers.users

import domain.users.UserLogActivities
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Created by Rosie on 2016/12/15.
 */
class UserLogActivitiesControllerTest extends PlaySpec with OneAppPerTest {
val userLog = UserLogActivities(
"email","001","s1","first log", new DateTime(),"first time loggin" )

"Routes" should {


"UserLogActivities Controller" should {

"Create User Log Activity Object in Controller" in {
val request =  route(app, FakeRequest(POST, "/userLogActivities/create")
.withJsonBody(Json.toJson(userLog)))
.get
status(request) mustBe OK
contentType(request) mustBe Some("application/json")
println(" The Content is ", contentAsString(request))
}

"Get User Log Activity From Controller" in {
val request = route(app, FakeRequest(GET, "/userLogActivities/get/id")
).get
status(request) mustBe OK
contentType(request) mustBe Some("application/json")
println(" The Output", contentAsJson(request))
}

"Get User Log Activities From Controller" in {
val request = route(app, FakeRequest(GET, "/userLogActivities/get/all")
).get
status(request) mustBe OK
contentType(request) mustBe Some("application/json")
println(" The Output", contentAsJson(request))
}


}
}

}

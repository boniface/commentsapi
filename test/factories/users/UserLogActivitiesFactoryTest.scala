package factories.users

import domain.users.UserLogActivities
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
 * Created by Rosie on 2016/09/29.
 */
class UserLogActivitiesFactoryTest extends FunSuite{

  test("getUserActivity"){
    val dates = new DateTime(2016,9,27,10,15,0,0)
    val values = Map("emailId"->"youremail@gmail.com","id"->"s123","sessionId"->"saul","details"->"here are the details","description"->"description_here")

    val userLog = UserLogActivitiesFactory.getUserActivity(values,dates)

    assert (userLog == UserLogActivities(emailId="youremail@gmail.com",id="s123",sessionId="saul",details="here are the details",date= new DateTime(2016,9,27,10,15,0,0),description="description_here"))
  }

}

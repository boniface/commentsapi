package factories.users

import domain.users.UserSessionEvent
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
 * Created by Rosie on 2016/09/29.
 */
class UserSessionEventFactoryTest extends FunSuite{

  test ("getUserSession"){
    val dates = new DateTime(2016,9,28,10,25,2,0)
    val values = Map("sessionId"->"user1","id"->"345","eventName"->"getSession")

    val userSessionEvent = UserSessionEventFactory.getUserSessionEvent(values,dates)

    assert (userSessionEvent == UserSessionEvent(sessionId="user1",id="345",eventTime= new DateTime(2016,9,28,10,25,2,0),eventName="getSession"))
  }

}

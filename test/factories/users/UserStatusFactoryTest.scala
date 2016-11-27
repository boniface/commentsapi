package factories.users

import domain.users.UserStatus
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
 * Created by Rosie on 2016/11/27.
 */
class UserStatusFactoryTest extends FunSuite{

  test ("getUserStatus"){
    val dates = new DateTime(2016,9,28,10,25,2,0)
    val values = Map("siteId"->"hub1","userId"->"admin","eventName"->"adminStat")

    val userStatus = UserStatusFactory.getUserStatus(values,dates)

    assert (userStatus == UserStatus(siteId="user1",userId="admin",status="adminStat",date= new DateTime(2016,9,28,10,25,2,0)))
  }
}

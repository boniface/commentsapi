package factories.users

import domain.users.UserSession
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
 * Created by Rosie on 2016/09/29.
 */
class UserSessionFactoryTest extends FunSuite{
  test("getUserSession"){
    val dates = new DateTime(2016,9,29,11,28,0,4)
    val values = Map("email"->"useremail@gmail.com","userSessionId"->"user1","ipaddress"->"155.238.8.86",
      "browserSession"->"chrome","status"->"active","tokenId"->"cr155")

    val userSession = UserSessionFactory.getUserSession(values,dates)

    assert (userSession == UserSession(email="useremail@gmail.com",userSessionId="user1",startTime= new DateTime(2016,9,29,11,28,0,4),ipaddress="155.238.8.86",
      browserSession="chrome",status="active",tokenId="cr155"))
  }

}

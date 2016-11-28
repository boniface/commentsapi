package factories.users

import domain.users.UserRole
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
 * Created by Rosie on 2016/09/29.
 */
class UserRoleFactoryTest extends FunSuite{
  test("getUserRole"){

    val dates = new DateTime(2016,9,27,10,15,0,0)

    val values = Map("emailId"->"youremail@gmail.com", "roleId"->"admin")

    val userRole = UserRoleFactory.getUserRole(values,dates)

    assert(userRole == UserRole(emailId="youremail@gmail.com", roleId="admin", date = new DateTime(2016,9,27,10,15,0,0)))
  }

}

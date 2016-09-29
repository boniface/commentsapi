package factories.users

import domain.users.UserRole
import org.scalatest.FunSuite

/**
 * Created by Rosie on 2016/09/29.
 */
class UserRoleFactoryTest extends FunSuite{
  test("getUserRole"){

    val values = Map("emailId"->"youremail@gmail.com", "roleId"->"admin")

    val userRole = UserRoleFactory.getUserRole(values)

    assert(userRole == UserRole(emailId="youremail@gmail.com", roleId="admin"))
  }

}

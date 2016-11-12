package factories.util

import domain.util.Roles
import org.scalatest.FunSuite

/**
  * Created by Quest on 2016/09/27.
  */
class RolesFactoryTest extends FunSuite{

  test("getRoles"){
    val values = Map("id"->"RoleId","rolename"->"admin")
    val getRoles = RolesFactory.getRoles(values)
    assert(getRoles == Roles(id = "RoleId",rolename = "admin"))
  }

}

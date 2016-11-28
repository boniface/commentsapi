package services.users

import domain.users.UserRole
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/28.
 */
class UserRoleServiceTest extends FunSuite {

  test("testSave") {
    val role = UserRole("myemail@yahoo.fr","user",new DateTime())
    val result = Await.result(UserRoleService.apply().save(role), 2.minutes)
    assert(result.isExhausted)
  }


  test("testGetAllRoles") {
    val result = Await.result(UserRoleService.apply().getAllRoles, 2.minutes)

    assert(result === "test")
  }

  test("testDeleteById") {
    val result = Await.result(UserRoleService.apply().deleteById("myemail@yahoo.fr"), 2.minutes)

    assert(result === "test")
  }

  test("testGetRoleById") {
    val result = Await.result(UserRoleService.apply().getRoleById("myemail@yahoo.fr"), 2.minutes)

    assert(result === "test")
  }


}

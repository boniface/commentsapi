package services.users

import domain.users.UserStatus
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by Rosie on 2016/11/28.
 */
class UserStatusServiceTest extends FunSuite{

  test("testSave"){
    val status = UserStatus("s002","001","available",new DateTime())
    val result = Await.result(UserStatusService.apply().save(status), 2minutes)

    assert(result.isExhausted)
  }

  test("testGetStatusByUserId"){
    val result = Await.result(UserStatusService.apply().getStatusByUserId("001"),2 minutes)
    assert(result === "test")
  }

  test("testGetAll") {
    val result = Await.result(UserStatusService.apply().getAllUserStatus, 2.minutes)

    assert(result === "test")
  }

  test("testDelete"){
    val result = Await.result(UserStatusService.apply().deleteById("001"), 2.minutes)

    assert(result === "test")
  }



}

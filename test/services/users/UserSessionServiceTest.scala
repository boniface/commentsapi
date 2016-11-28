package services.users

import domain.users.UserSession
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/28.
 */
class UserSessionServiceTest extends FunSuite {

  test("testSave") {
    val session = UserSession("email@yahoo.fr","s001",new DateTime(),"155.238.4.86","chrome101","available","1")
    val result = Await.result(UserSessionService.apply().save(session), 2.minutes)
    assert(result.isExhausted)
  }


  test("testGetAllUserSessions") {
    val result = Await.result(UserSessionService.apply().getAllUserSessions, 2.minutes)

    assert(result === "test")
  }

  test("testDeleteById") {
    val result = Await.result(UserSessionService.apply().deleteById("s001"), 2.minutes)

    assert(result === "test")
  }

  test("testGetUserSessionById") {
    val result = Await.result(UserSessionService.apply().getUserSessionById("s001"), 2.minutes)

    assert(result === "test")
  }



}

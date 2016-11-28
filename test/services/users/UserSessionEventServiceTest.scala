package services.users

import domain.users.UserSessionEvent
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
 * Created by Rosie on 2016/11/28.
 */
class UserSessionEventServiceTest extends FunSuite{

  test("testSave"){
    val sessionEvt = UserSessionEvent("0001","001", new DateTime(),"login")
    val result = Await.result(UserSessionEventService.apply().save(sessionEvt), 2minutes)

    assert(result.isExhausted)
  }

  test("testEventById"){
    val result = Await.result(UserSessionEventService.apply().getEventById("001"),2 minutes)
    assert(result === "test")
  }

  test("testGetAll") {
    val result = Await.result(UserSessionEventService.apply().getAllSessionEvent, 2.minutes)

    assert(result === "test")
  }

  test("testDelete"){
    val result = Await.result(UserSessionEventService.apply().deleteById("001"), 2.minutes)

    assert(result === "test")
  }

}

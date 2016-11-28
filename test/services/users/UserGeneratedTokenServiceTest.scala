package services.users

import domain.users.UserGeneratedToken
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/28.
 */
class UserGeneratedTokenServiceTest extends FunSuite{

  test("testSave") {
    val genToken = UserGeneratedToken("1","Available","online","hub1")
    val result = Await.result(UserGeneratedTokenService.apply().save(genToken), 2.minutes)
    assert(result.isExhausted)
  }

  test("testStatusById") {
    val result = Await.result(UserGeneratedTokenService.apply().getStatusById("1"), 2.minutes)

    assert(result === "test")
  }

  test("testGetAll") {
    val result = Await.result(UserGeneratedTokenService.apply().getAllTokens, 2.minutes)

    assert(result === "test")
  }

}

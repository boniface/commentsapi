package services.users

import domain.users.Reputation
import org.joda.time.DateTime
import org.scalatest.FunSuite
import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/28.
 */
class ReputationServiceTest extends FunSuite{

//  test("testSave"){
//    val reputation = Reputation("usermail@gmail.com",new DateTime(2016,11,18,6,0,0,0),5)
//    val result = Await.result(ReputationService.apply().save(reputation), 2minutes)
//
//    assert(result.isExhausted)
//  }
//
//  test("testGetReputationById"){
//    val result = Await.result(ReputationService.apply().getReputationById("usermail@gmail.com"),2 minutes)
//    assert(result === "test")
//  }
//
//  test("testDelete"){
//    val result = Await.result(ReputationService.apply().deleteById("usermail@gmail.com"), 2.minutes)
//
//    assert(result === "test")
//  }
//
//  test("testGetAll") {
//    val result = Await.result(ReputationService.apply().getAllReputation, 2.minutes)
//
//    assert(result === "test")
//  }
}

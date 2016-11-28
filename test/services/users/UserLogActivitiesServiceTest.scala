package services.users

import domain.users.UserLogActivities
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/28.
 */
class UserLogActivitiesServiceTest extends FunSuite {

  test("testSave") {
    val log = UserLogActivities("myemail@yahoo.fr","001","0001","log1",new DateTime(),"description")
    val result = Await.result(UserLogActivitiesService.apply().save(log), 2.minutes)
    assert(result.isExhausted)
  }


  test("testGetAllLogActivities") {
    val result = Await.result(UserLogActivitiesService.apply().getAllLogActivities, 2.minutes)

    assert(result === "test")
  }

  test("testDeleteById") {
    val result = Await.result(UserLogActivitiesService.apply().deleteById("001"), 2.minutes)

    assert(result === "test")
  }

  test("testGetLogDetailsById") {
    val result = Await.result(UserLogActivitiesService.apply().getLogDetailsById("001"), 2.minutes)

    assert(result === "test")
  }

}

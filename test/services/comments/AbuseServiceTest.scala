package services.comments

import domain.comments.Abuse
import org.joda.time.DateTime
import org.scalatest.FunSuite
import scala.concurrent.Await
import scala.concurrent.duration._


/**
  * Created by Bonga on 11/17/2016.
  */
class AbuseServiceTest extends FunSuite {


  test("testSave") {
    val abuse = Abuse("100", "200", "300", "400", "wwew", "bmabulu@webmail.co.za",DateTime)
    val result = Await.result(AbuseService.apply().save(abuse),2.minutes)
    assert(result.isExhausted)
  }

  test("testAbuse") {
    val result = Await.result(AbuseService.apply().getAbuseBySubjectId("100"), 2.minutes)
    assert(result === "test")
  }

  test("testGetAllAbuse") {
    val result = Await.result(AbuseService.apply().getAllAbuse, 2.minutes)

    assert(result === "test")
  }

  test("testDeleteAll") {
    val result = Await.result(AbuseService.apply().deleteAll, 2.minutes)

    assert(result === "test")
  }


}

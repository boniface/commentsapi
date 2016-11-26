package services.comments

import domain.comments.Response
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Bonga on 11/17/2016.
  */
class ResponseServiceTest  extends FunSuite{

  test("testSave") {
    val response = Response("300", "600", "ewe", "cmabulu@webmail.co.za", "derere",DateTime)
    val result = Await.result(ResponseService.apply().save(response),2.minutes)
    assert(result.isExhausted)
  }

  test("testResponse") {
    val result = Await.result(ResponseService.apply().getResponseByCommentId("300"),2.minutes)
    assert(result === "test")
  }

  test("testGetAllResponse") {
    val result = Await.result(ResponseService.apply().getAllResponse ,2.minutes  )

    assert(result === "test")
  }

  test("testDeleteAll") {
    val result = Await.result(ResponseService.apply().deleteAll, 2.minutes)

    assert(result === "test")
  }


}

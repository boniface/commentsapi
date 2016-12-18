package services.comments

import domain.comments.ResponseStatus
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Bonga on 11/17/2016.
  */
class ResponseItemStatusServiceTest extends FunSuite {

  test("testSave") {
    val responseStatus = ResponseStatus("400", "Hayi",DateTime)
    val result = Await.result(ResponseStatusService.apply().save(responseStatus), 2.minutes)
    assert(result.isExhausted)
  }

  test("testResponseStatus") {
    val result = Await.result(ResponseStatusService.apply().getResponseStatusBySubjectId("400"), 2.minutes)
    assert(result === "test")
  }

  test("testGetAllResponseStatus") {
    val result = Await.result(ResponseStatusService.apply().getAllResponseStatus , 2.minutes)

    assert(result === "test")
  }

  test("testDeleteAll") {
    val result = Await.result(ResponseStatusService.apply().deleteAll , 2.minutes)

    assert(result === "test")
  }

}

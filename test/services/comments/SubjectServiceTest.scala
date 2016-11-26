package services.comments

import domain.comments.{Subject, Subject}
import org.joda.time.DateTime
import org.scalatest.FunSuite
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Bonga on 11/17/2016.
  */
class SubjectServiceTest extends FunSuite {

  test("testSave") {
    val subject = Subject("100", "200", "Sizwe","gfhghggu",DateTime)
    val result = Await.result(SubjectService.apply().save(subject), 2.minutes)
    assert(result.isExhausted)
  }

  test("testSubject") {
    val result = Await.result(SubjectService.apply().getSubjectBySubjectId("100"), 2.minutes)
    assert(result === "test")
  }

  test("testGetAllSubject") {
    val result = Await.result(SubjectService.apply().getAllSubject , 2.minutes)

    assert(result === "test")
  }

  test("testDeleteAll") {
    val result = Await.result(SubjectService.apply().deleteAll , 2.minutes)

    assert(result === "test")
  }



}

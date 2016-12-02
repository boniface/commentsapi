package services.comments

import domain.comments.CommentStatus
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Bonga on 11/17/2016.
  */
class CommentStatusServiceTest  extends FunSuite {


  test("testSave") {
    val commentStatus = CommentStatus("100", "hehe",DateTime)
    val result = Await.result(CommentStatusService.apply().save(commentStatus),2.minutes)
    assert(result.isExhausted)
  }

  test("testCommentStatus") {
    val result = Await.result(CommentStatusService.apply().getCommentStatusBySubjectId("100"), 2.minutes)
    assert(result === "test")
  }

  test("testGetAllCommentStatus") {
    val result = Await.result(CommentStatusService.apply().getAllCommentStatus,2.minutes)

    assert(result === "test")
  }

  test("testDeleteAll") {
    val result = Await.result(CommentStatusService.apply().deleteAll, 2.minutes)

    assert(result === "test")
  }


}

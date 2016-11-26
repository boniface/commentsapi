package services.comments

import domain.comments.{Comment, Comment}
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.comments.CommentService
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Bonga on 11/17/2016.
  */
class CommentServiceTest extends FunSuite {


  test("testSave") {
    val comment = Comment("100", "200", "500", "amabulu@webmail.co.za", "191.2.3.0","Hayi mani",DateTime)
    val result = Await.result(CommentService.apply().save(comment),2.minutes)
    assert(result.isExhausted)
  }

  test("testComment") {
    val result = Await.result(CommentService.apply().getCommentBySubjectId("100"), 2.minutes)
    assert(result=== "test")
  }

  test("testGetAllComment") {
    val result = Await.result(CommentService.apply().getAllComment, 2.minutes)

    assert(result === "test")
  }

  test("testDeleteAll") {
    val result = Await.result(CommentService.apply().deleteAll, 2.minutes)

    assert(result === "test")
  }


}

package repositories.comments

import domain.comments.{CommentStatus, Abuse}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Bonga on 11/20/2016.
  */
class CommentItemStatusTest  extends FeatureSpec with GivenWhenThen{


  feature("Create commentstatus") {
    info("Add an commentstatus")
    scenario("Add new commentstatus ") {
      Given("Given commentId,status,date")
      val commentId = "2"
      val status = "2"
      val date = DateTime

      Then("Add commentstatus ")
      val comms = CommentStatus(commentId,status,date)
      val commentstatusRepo = CommentStatusRepository
      commentstatusRepo.save(comms)
      Then("Display All ")
      val displayAllstatuses = Await.result(commentstatusRepo.getSiteCommentStatus(commentId), 2 minutes)
      displayAllstatuses.foreach(i => println("CommentStatus=======>", i))
      val displayIdStatus = Await.result(commentstatusRepo.getCommentBycommentId(commentId), 2 minutes)
      displayIdStatus.foreach(i => println("CommentStatus=======>", i))
    }
  }


}

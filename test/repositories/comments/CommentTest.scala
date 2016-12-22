package repositories.comment
import domain.comments.Comment
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repositories.comments.CommentRepository
import scala.concurrent.duration._
import scala.concurrent.Await
/**
  * Created by Bonga on 11/20/2016.
  */
class CommentTest extends FeatureSpec with GivenWhenThen{


//  feature("Create comment") {
//    info("Add an comment")
//    scenario("Add new comment ") {
//      Given("Given siteId,subjectId,commentId,emailId,ipaddress,comment,date")
//      val siteId = "3"
//      val subjectId = "3"
//      val commentId = "3"
//      val emailId = "3"
//      val ipaddress = "3"
//      val comment = "3"
//      val date = DateTime
//
//      Then("Add comment ")
//      val comm = Comment(siteId,subjectId,commentId,emailId,ipaddress,comment,date)
//      val commentRepo = CommentRepository
//      commentRepo.save(comm)
//      Then("Display All ")
//      val displayAll = Await.result(commentRepo.getSiteComment(siteId), 2 minutes)
//      displayAll.foreach(i => println("Comment=======>", i))
//      val displayIdStatus = Await.result(commentRepo.getCommentBySubjectId(siteId,subjectId), 2 minutes)
//      displayIdStatus.foreach(i => println("Comment=======>", i))
//    }
//  }


}

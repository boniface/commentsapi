package repositories.votes
import domain.votes.VoteUp
import repositories.Votes.VoteUpRepository
import org.scalatest.{FeatureSpec, GivenWhenThen}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by fatimam on 07/12/2016.
  */
class VoteUpTest  extends FeatureSpec with GivenWhenThen
{
    info("Add a Vote Up")
    scenario("Add new Voter Up ") {
      Given("Given commentIdOrResponseId,emailId,ipaddress,count")
      val commentIdOrResponseId = "1"
      val emailId = "1"
      val ipaddress = "1"
      val count=1

      Then("Add Voter Register ")
      val admin = VoteUp(commentIdOrResponseId,emailId,ipaddress,count)
      val voteUpRepo = VoteUpRepository
      voteUpRepo.save(admin)
      Then("Display All ")
      val displayAllstatuses = Await.result(voteUpRepo.getAllkeys, 2 minutes)
      displayAllstatuses.foreach(i => println("Vote Up=======>", i))
      val displayIdStatus = Await.result(voteUpRepo.getVoteUpById(commentIdOrResponseId), 2 minutes)
      displayIdStatus.foreach(i => println("ItemStatus=======>", i))
    }
}



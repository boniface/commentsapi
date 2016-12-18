package repositories.votes
import domain.votes.VoterRegister
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repositories.Votes.VoterRegisterRepository
import scala.concurrent.duration._
import scala.concurrent.Await
/**
  * Created by fatimam on 07/12/2016.
  */
class VoterRegisterTest extends FeatureSpec with GivenWhenThen
{
  feature("Create Voter Register") {
    info("Add a Voter Register")
    scenario("Add new Voter Register ") {
      Given("Given commentIdOrResponseId,emailId,ipaddress")
      val commentIdOrResponseId = "1"
      val emailId = "1"
      val ipaddress = "1"

      Then("Add Voter Register ")
      val admin = VoterRegister(commentIdOrResponseId,emailId,ipaddress)
      val voterRegisterRepo = VoterRegisterRepository
      voterRegisterRepo.save(admin)
      Then("Display All ")
      val displayAllstatuses = Await.result(voterRegisterRepo.getAllkeys, 2 minutes)
      displayAllstatuses.foreach(i => println("Voter Register=======>", i))
      val displayIdStatus = Await.result(voterRegisterRepo.getVoterRegisterById(commentIdOrResponseId), 2 minutes)
      displayIdStatus.foreach(i => println("ItemStatus=======>", i))
    }
  }

}

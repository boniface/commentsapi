package repositories.users

import domain.users.Reputation
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/18.
 */
class ReputationRepoTest extends FeatureSpec with GivenWhenThen{

  feature("Create Reputation") {
    info("Add a Reputation")
    scenario("Add a Reputation") {
      Given("Given A emailId,date,value")
      val emailId ="user1@gmail.com"
      val date = new DateTime(2016,11,27,6,0,0,0)
      val value = 1
      Then("Add Reputation ")
      val titletext = Reputation(emailId,date,value)
      val reputationRepo = ReputationRepository
      reputationRepo.save(titletext)
      Then("Display All ")
      val displayTokenId = Await.result(ReputationRepository.getReputationByEmailId(emailId), 2 minutes)
      displayTokenId.foreach(i => println("Reputation=======>",i))
    }
  }
}

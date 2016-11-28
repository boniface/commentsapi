package repositories.users

import domain.users.UserLogActivities
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}

import scala.concurrent.Await

/**
 * Created by Rosie on 2016/11/18.
 */
class UserLogActivitiesRepoTest extends FeatureSpec with GivenWhenThen{

  feature("Create UserLog") {
    info("Add a UserLog")
    scenario("Add a UserLog") {
      Given("Given A id,sessionId,emailId,details,date,description")
      val id ="100"
      val sessionId="s100"
      val emailId="user1"
      val details= "user1 logged in"
      val date = new DateTime(2016,11,22,12,0,0,0)
      val description =" user1 s100 logged"

      Then("Add a UserLog ")
      val titletext = UserLogActivities(id,sessionId,emailId,details,date,description)
      val LogRepo = UserLogActivitiesRepository
      UserLogActivitiesRepository.save(titletext)
      Then("Display All ")
      val displayLog = Await.result(UserLogActivitiesRepository.getLogDetails(id), 2 minutes)
      displayLog.foreach(i => println("Log=======>",i))
    }
  }

}

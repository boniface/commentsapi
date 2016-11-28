package repositories.users

import domain.users.UserSession
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/18.
 */
class UserSessionRepoTest extends FeatureSpec with GivenWhenThen{
  feature("Create Session") {
    info("Add a Session")
    scenario("Add a Session") {
      Given("Given A email,userSessionId,startTime,ipaddress,browserSession,status,tokenId")
      val userSessionId ="1111"
      val tokenId="122"
      val email= "user1@gmail.com"
      val ipaddress = "155.238.4.86"
      val browserSession = "chrome1254"
      val status = "on"
      val startTime = new DateTime(2016,11,27,6,3,0,0)

      Then("Add Session ")
      val titletext = UserSession(email,userSessionId,startTime,ipaddress,browserSession,status,tokenId)
      val sessionRepo = UserSessionRepository
      sessionRepo.save(titletext)
      Then("Display All ")
      val displaySessionId = Await.result(UserSessionRepository.getUserSessionById(tokenId), 2 minutes)
      displaySessionId.foreach(i => println("Session=======>",i))

      val displayEmail = Await.result(UserSessionRepository.getEmailByUserSessionId((userSessionId)), 2 minutes)
      displayEmail.foreach(i => println("SessionEmail=======>",i))
    }
  }

}

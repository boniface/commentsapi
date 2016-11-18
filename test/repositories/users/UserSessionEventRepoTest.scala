package repositories.users

import domain.users.{UserSessionEvent, UserSession}
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/18.
 */
class UserSessionEventRepoTest extends FeatureSpec with GivenWhenThen{
  feature("Create Session") {
    info("Add a Session")
    scenario("Add a Session") {
      Given("Given A sessionId,id,eventTime,eventName")
      val sessionId ="1000"
      val id="122"
      val eventTime = new DateTime(2016,11,27,6,0,0,0)
      val eventName = "login"
      Then("Add Session ")
      val titletext = UserSessionEvent(id,sessionId,eventTime,eventName)
      val TokenRepo = UserSessionEventRepository
      TokenRepo.save(titletext)
      Then("Display All ")
      val displayEventNameById = Await.result(UserSessionEventRepository.getEventNameById(id,sessionId), 2 minutes)
      displayEventNameById.foreach(i => println("Result=======>",i))

      val displayById = Await.result(UserSessionEventRepository.getSessionEvent(id), 2 minutes)
      displayById.foreach(i => println("Event=======>",i))
    }
  }

}

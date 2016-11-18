package repositories.users

import domain.users.UserGeneratedToken
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/18.
 */
class UserGeneratedTokenRepoTest extends FeatureSpec with GivenWhenThen{

  feature("Create User Generated Token") {
    info("Add a Token")
    scenario("Add a Token") {
      Given("Given A token, siteId, status, message")
      val token ="12981"
      val siteId="github"
      val status= "on"
      val message= "token for user1 on github"
      Then("Add UserToken ")
      val titletext = UserGeneratedToken(token, siteId, status, message)
      val TokenRepo = UserTokenRepository
      TokenRepo.save(titletext)
      Then("Display All ")
      val displayStatusById = Await.result(UserTokenRepository.getStatusBySiteId(siteId,token), 2 minutes)
      displayStatusById.foreach(i => println("Status=======>",i))
    }
  }

}

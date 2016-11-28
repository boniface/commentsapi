package repositories.users

import domain.users.User
import org.scalatest.{GivenWhenThen, FeatureSpec}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/18.
 */
class UserRepoTest extends FeatureSpec with GivenWhenThen {

  feature("Create User") {
    info("New User")
    scenario("New User") {
      Given("Given A siteid,email,screenName,firstname,lastName,password")
      val siteId ="github"
      val email="user1@gmail.com"
      val screenName="user1"
      val firstname=Some("marc")
      val lastName=Some("antoine")
      val password="mac1"

      Then("Add User ")
      val titletext = User(siteId,email,screenName,firstname,lastName,password)
      val userRepo = UserRepository
      UserRepository.save(titletext)
      Then("Display All ")
      val displayUserId = Await.result(UserRepository.getSiteUsers(siteId), 2 minutes)
      displayUserId.foreach(i => println("User=======>",i))
    }
  }


}

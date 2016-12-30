package repositories.users

import org.scalatest.{FeatureSpec, GivenWhenThen}

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
      
      Then("Add User ")
      val userRepo = UserRepository

      Then("Display All ")
      val displayUserId = Await.result(UserRepository.getSiteUsers("HASHCODE"), 2 minutes)
      displayUserId.foreach(i => println("User=======>",i))
    }
  }


}

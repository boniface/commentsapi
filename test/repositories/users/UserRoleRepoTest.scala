package repositories.users

import domain.users.UserRole
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by Rosie on 2016/11/18.
 */
class UserRoleRepoTest extends FeatureSpec with GivenWhenThen{

  feature("Create User Role") {
    info("Admin Add a User Role")
    scenario("Admin Add a User Role") {
      Given("Given A emailId,roleId,date")
      val emailId ="user1"
      val roleId="1user1"
      val date = new DateTime(2016,11,27,6,1,0,0)

      Then("Add User Role ")
      val titletext = UserRole(emailId,roleId,date)
      val userRoleRepo = UserRoleRepository
      userRoleRepo.save(titletext)
      Then("Display All ")
      val displayRoleByEmailId = Await.result(UserRoleRepository.getRoleByEmailId(emailId), 2 minutes)
      displayRoleByEmailId.foreach(i => println("Result=======>",i))

      val displayRoleId = Await.result(UserRoleRepository.getRoleId(emailId,roleId ),1 minute)
      displayRoleId.foreach(i=>println("Role ======>",i))
    }
  }
}

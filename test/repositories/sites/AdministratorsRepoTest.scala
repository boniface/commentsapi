package repositories.sites

import domain.sites.Administrators
import org.scalatest.{FeatureSpec, GivenWhenThen, FunSuite}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/20.
  */
class AdministratorsRepoTest extends FeatureSpec with GivenWhenThen{

  feature("Create Administrators") {
    info("Admin add a Administrator")
    scenario("Admin add new Administrator ") {
      Given("Given siteId,emailId")
      val siteId = "101"
      val emailId = "admin1@admin.com"
      Then("Add Administrator ")
      val admin = Administrators(siteId,emailId)
      val adminRepo = AdministratorsRepository
      adminRepo.saveAdministrator(admin)
      Then("Display All ")
      val displayAllAdministrators = Await.result(adminRepo.getAdministrators, 2 minutes)
      displayAllAdministrators.foreach(i => println("Administrators=======>", i))
      val displayIdAdmins = Await.result(adminRepo.getAdministratorsBySiteId(siteId,emailId), 2 minutes)
      displayIdAdmins.foreach(i => println("Administrators=======>", i))
    }
  }
}

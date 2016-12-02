package repositories.sites

import domain.sites.AdministratorHistory
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/20.
  */
class AdministratorHistoryRepoTest extends FeatureSpec with GivenWhenThen{

  feature("Create Administrators") {
    info("Admin add a Administrator")
    scenario("Admin add new Administrator ") {
      Given("Given siteId,emailId")
      val siteId = "101"
      val emailId = "admin1@admin.com"
      val adminStatusId = "1"
      val date = DateTime
      Then("Add Administrator ")
      val admin = AdministratorHistory(siteId,emailId,adminStatusId,date)
      val adminRepo = AdministratorHistoryRepository
      adminRepo.save(admin)
      Then("Display All ")
      val displayAllAdministrators = Await.result(adminRepo.getAllAdministratorsHistory, 2 minutes)
      displayAllAdministrators.foreach(i => println("Administrators=======>", i))
      val displayIdAdmins = Await.result(adminRepo.getAdministratorHistoryByDate(date), 2 minutes)
      displayIdAdmins.foreach(i => println("Administrators=======>", i))
    }
  }
}

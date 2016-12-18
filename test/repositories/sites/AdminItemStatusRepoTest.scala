package repositories.sites

import domain.sites.AdminStatus
import org.scalatest.{FeatureSpec, GivenWhenThen, FunSuite}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/20.
  */
class AdminItemStatusRepoTest extends FeatureSpec with GivenWhenThen{

  feature("Create adminStatus") {
    info("Admin add a adminStatus")
    scenario("Admin add new adminStatus ") {
      Given("Given adminStatusId,name")
      val adminStatusId = "1"
      val name = "admin1"
      Then("Add adminStatus ")
      val admin = AdminStatus(adminStatusId,name)
      val adminRepo = AdminStatusRepository
      adminRepo.save(admin)
      Then("Display All ")
      val displayAllstatuses = Await.result(adminRepo.getAllAdminStatus, 2 minutes)
      displayAllstatuses.foreach(i => println("AdminStatus=======>", i))
      val displayIdStatus = Await.result(adminRepo.getAdminStatusById(adminStatusId), 2 minutes)
      displayIdStatus.foreach(i => println("AdminStatus=======>", i))
    }
  }
}

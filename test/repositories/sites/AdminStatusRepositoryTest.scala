package repositories.sites

import domain.sites.AdminStatus
import org.scalatest.{GivenWhenThen, FeatureSpec}
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Quest on 2016/11/03.
  */
class AdminStatusRepositoryTest extends FeatureSpec with GivenWhenThen{

  feature("Create adminStatus"){
    info("Create admin")
    scenario("Create admin"){
      Given("adminStatusId,name")
      val adminStatusId = "1"
      val name = "administrator"
      Then("Create adminStatus")
      val createStatus = AdminStatus(adminStatusId, name)
      val repo = AdminStatusRepository
      repo.save(createStatus)
      Then("display admin status")
      val displayTokenId = Await.result(repo.getAdminStatusById("1"), 2 minutes)
      displayTokenId.foreach(i => println("Status=======>",i))
    }
  }
}

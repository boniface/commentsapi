package repositories.sites

import domain.sites.AdminStatus
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repositories.sites.AdminStatusRepository
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by Quest on 2016/11/03.
  */
class AdminStatusRepositoryTest extends FeatureSpec with GivenWhenThen{

  feature("Create adminStatus")(_)
  info("Create admin")
  scenario("Create admin")(_)
  Given("adminStatusId,name")
  val adminStatusId = "1"
  val name = "administrator"
  Then("Create adminStatus")
  val createStatus = AdminStatus(adminStatusId,name)
  val repo = AdminStatusRepository
  repo.save(createStatus)
  Then("display admin status")
  val duration = Duration.fromNanos(1000)
  val displayStatus = Await.result(repo.getAllAdminStatus,duration)
  displayStatus.foreach(i=>println("AdminStatus====> ",i))
}

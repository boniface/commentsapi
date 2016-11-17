package repositories.sites

import domain.sites.AdministratorHistory
import org.joda.time.DateTime
import org.scalatest._
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/15.
  */
class AdministratorHistoryRepositoryTest extends FunSpec with GivenWhenThen{

  feature("Create adminHistory"){
    info("Create adminHistory")
    scenario("Create History"){
      Given("siteId,emailId,adminStatusId,date")
      val siteId = "123"
      val emailId = "administrator"
      val adminStatusId = "303"
      val date = DateTime
      Then("Create History")
      val createStatus = AdministratorHistory(siteId,emailId,adminStatusId,date)
      val repo = AdministratorHistoryRepository
      repo.save(createStatus)
      Then("display admin history")
      val displayTokenId = Await.result(repo.getAdministratorHistoryByDate(date), 2 minutes)
      displayTokenId.foreach(i => println("AdminHistory=======>",i))
    }
  }

}

package repositories.sites
import domain.sites.Administrators
import org.scalatest.{GivenWhenThen, FeatureSpec}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/15.
  */
class AdministratorsRepositoryTest extends FeatureSpec with GivenWhenThen{

  feature("Create administrators"){
    info("Create administrator")
    scenario("Create admin"){
      Given("SiteId,emailId")
      val siteId = "303"
      val emailId = "administrator"
      Then("Create administrator")
      val createStatus = Administrators(siteId, emailId)
      val repo = AdministratorsRepository
      repo.saveAdministrator(createStatus)
      Then("display administrators")
      val displayTokenId = Await.result(repo.getAdministratorsBySiteId("303","administrator"), 2 minutes)
      displayTokenId.foreach(i => println("Administrators=======>",i))
    }
  }

}

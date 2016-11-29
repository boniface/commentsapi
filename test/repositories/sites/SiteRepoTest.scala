package repositories.sites

import domain.sites.Site
import org.scalatest.{FeatureSpec, GivenWhenThen}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/20.
  */
class SiteRepoTest extends FeatureSpec with GivenWhenThen {

  feature("Create site") {
    info("Admin add a site")
    scenario("Admin add new site ") {
      Given("Given siteId,name,url")
      val siteId = "101"
      val name = "youtube"
      val url = "www.youtube.com"
      Then("Add site ")
      val site = Site(siteId, name, url)
      val siteRepo = SiteRepository
      siteRepo.saveSite(site)
      Then("Display All ")
      val displayAllsites = Await.result(siteRepo.getAllSites, 2 minutes)
      displayAllsites.foreach(i => println("Sites=======>", i))
      val displayIdSites = Await.result(siteRepo.getSiteByName(siteId, name), 2 minutes)
      displayIdSites.foreach(i => println("Sites=======>", i))
    }
  }
}
package repositories.sites

import domain.sites.Site
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repositories.sites.SiteRepository

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scalaz.std.int

/**
  * Created by Quest on 2016/10/29.
  */
class SitesRepositoryTest extends FeatureSpec with GivenWhenThen{

  feature("Create site"){
    info("Admin add site")
    scenario("Admin add site"){
      Given("siteId,  name and url")
      val siteId = "132"
      val name = "youTube"
      val url = "www.youtube.com"
      Then("Create site")
      val Createdsite = Site(siteId,name,url)
      val SiteRepo = SiteRepository
      SiteRepo.saveSite(Createdsite)

    }


  }

}

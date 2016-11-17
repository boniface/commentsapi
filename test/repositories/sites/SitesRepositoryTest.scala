package repositories.sites

import domain.sites.Site
import org.scalatest.{GivenWhenThen, FeatureSpec}
import scala.concurrent.duration._
import scala.concurrent.Await


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
      val Createsite = Site(siteId,name,url)
      val SiteRepo = SiteRepository
      SiteRepo.saveSite(Createsite)
      Then("Display All ")
      val displayTokenId = Await.result(SiteRepo.getSiteByName("132","youTube"), 2 minutes)
      displayTokenId.foreach(i => println("Site=======>",i))
    }


  }

}

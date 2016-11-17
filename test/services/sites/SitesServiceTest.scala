package services.sites

import domain.sites.Site
import org.scalatest.FunSuite
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Quest on 2016/10/29.
  */
class SitesServiceTest extends FunSuite{

  test("Save Site test"){
    val site = Site("112","youtube","www.youtube.com")
    val result = Await.result(SitesService.apply().save(site),2 minutes)
    assert(result.wasApplied())
  }

  test("getSiteById"){
    val result = Await.result(SitesService.apply().getSiteById("112","youtube"),2 minutes)
    assert(result.get.siteId === "112")
  }

  test("getAllSites"){
    val result = Await.result(SitesService.apply().getAllSites,2 minutes)
    assert(result.head.siteId === "112")
  }

  test("updateSite"){
    val site = Site("112","youtube","www.youtube.com")
    val result = Await.result(SitesService.apply().updateSite(site),2 minutes)
    assert(result.wasApplied())
  }
}

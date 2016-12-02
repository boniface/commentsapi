package services.sites

import domain.sites.Site
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Quest on 2016/11/20.
  */
class SiteServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val site = Site(
      "SITEID",
      "TEST SITE",
      "www.test.com")

    val result = Await.result(SitesService.apply.save(site), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetSIte") {
    val result = Await.result(SitesService.apply.getSiteById("SITEID"), 2.minutes)
    assert( result.get.name === "TEST SITE")
  }

  test("testGetAllSites") {
    val result = Await.result(SitesService.apply.getAllSites, 2.minutes)
    assert( result.size> 0)
  }
}

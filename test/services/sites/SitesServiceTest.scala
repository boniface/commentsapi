package services.sites

import domain.sites.Site
import org.scalatest.FunSuite
import services.sites.SitesService

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by Quest on 2016/10/29.
  */
class SitesServiceTest extends FunSuite{

  test("Save Site test"){
    val duration = Duration.fromNanos(1000000)
    val site = Site("112","youtube","www.youtube.com")
    val result = Await.result(SitesService.apply().save(site),duration)
    assert(result.isExhausted)
  }

}

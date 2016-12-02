package repositories.sites

import conf.connection.DataConnection
import domain.sites.Site
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Quest on 2016/11/20.
  */
class SiteRepoTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    SiteRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val site = Site(
      "SITEID",
      "TEST SITE",
      "www.test.com")

    val result = Await.result(SiteRepository.saveSite(site), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetSIte") {
    val result = Await.result(SiteRepository.getSiteById("SITEID"), 2.minutes)
    assert( result.get.name === "TEST SITE")
  }

  test("testGetAllSites") {
    val result = Await.result(SiteRepository.getAllSites, 2.minutes)
    assert( result.size > 0)
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    SiteRepository.truncate().future()
  }
}

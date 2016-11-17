package services.sites

import domain.sites.Administrators
import org.scalatest.FunSuite
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/17.
  */
class AdministratorsServiceTest extends FunSuite{

  test("Create and save"){
    val admin = Administrators("112","admin101")
    val results = Await.result(AdministratorsService.apply().saveAdministrator(admin),2.minutes)
    assert(results.isExhausted)
  }

  test("getAdministratorsBySiteId"){
    val  results = Await.result(AdministratorsService.apply().getAdministratorsBySiteId("112","admin101"), 2.minutes)
    assert(results.get.siteId === "112")
  }

  test("deleteAdministratorBySiteId"){
   val results = Await.result(AdministratorsService.apply().deleteAdministratorBySiteId("112"),2.minutes)
    assert(results.wasApplied())
  }

  test("getAdministrators"){
    val resultsIs = Await.result(AdministratorsService.apply().getAdministrators,2.minutes)
    assert(resultsIs.head.siteId === "112")
  }
}

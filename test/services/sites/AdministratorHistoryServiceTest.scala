package services.sites



import domain.sites.AdministratorHistory
import org.joda.time.DateTime
import org.scalatest.FunSuite
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/17.
  */
class AdministratorHistoryServiceTest extends FunSuite{

  test("Create and save adminHistory"){
    val adminHistory = AdministratorHistory("112","@admin101","1",new DateTime)
    val results = Await.result(AdministratorHistoryService.apply().save(adminHistory),2.minutes)
    assert(results.wasApplied())
  }
  test("getAdministratorHistoryByDate"){
    val results = Await.result(AdministratorHistoryService.apply().getAdministratorHistoryByDate(new DateTime()),2.minutes)
    assert(results.get.siteId === "112")
  }
  test("getAllAdministratorsHistory"){
    val results = Await.result(AdministratorHistoryService.apply().getAllAdministratorsHistory, 2.minutes)
    assert(results.head.siteId === "112")
  }
  test("deleteAll"){
    val results = Await.result(AdministratorHistoryService.apply().deleteAll, 3.minutes)
    assert(results.wasApplied())
  }

}

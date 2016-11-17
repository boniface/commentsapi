package services.sites

import domain.sites.AdminStatus
import org.scalatest.FunSuite
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/17.
  */
class AdminStatusServiceTest  extends FunSuite{

  test("Create admin Status"){
    val adminStatus = AdminStatus("1","administrator")
    val results = Await.result(AdminStatusService.apply().save(adminStatus),2.minutes)
    assert(results.wasApplied())
  }

  test("getAdminStatusById"){
    val results = Await.result(AdminStatusService.apply().getAdminStatusById("1"),2.minutes)
    assert(results.get.name === "administrator")
  }

  test("getAllAdminStatus"){
    val results = Await.result(AdminStatusService.apply().getAllAdminStatus,2.minutes)
    assert(results.head.adminStatusId === "1")
  }

  test("deleteById"){
    val results = Await.result(AdminStatusService.apply().deleteById("1"),2.minutes)
    assert(results.isExhausted)
  }
}

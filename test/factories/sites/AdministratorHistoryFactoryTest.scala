package factories.sites

import domain.sites.AdministratorHistory
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by Quest on 2016/09/27.
  */
class AdministratorHistoryFactoryTest extends FunSuite{

  test("getAdministratorHistory"){

    val values = Map("siteId"->"1045","emailId"->"@admin","adminStatusId"->"admin")

    val dates = new DateTime()

    val adminHistory = AdministratorHistoryFactory.getAdministratorHistory(values,dates)
      assert(adminHistory==AdministratorHistory(siteId = "1045",emailId = "@admin",adminStatusId = "admin",date = dates))
  }

}

package factories.sites

import domain.sites.Administrators
import org.scalatest.FunSuite

/**
  * Created by Quest on 2016/09/27.
  */
class AdministratorsFactoryTest extends FunSuite{

  test("getAdministrators"){
    val values= Map("siteId"->"1045","emailId"->"@administrator")
    val administrators = AdministratorsFactory.getAdministrators(values)
    assert(administrators==Administrators(siteId = "1045",emailId = "@administrator"))
  }
}

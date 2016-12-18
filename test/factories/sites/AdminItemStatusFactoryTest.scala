package factories.sites

import domain.sites.AdminStatus
import org.scalatest.FunSuite

/**
  * Created by Quest on 2016/09/27.
  */
class AdminItemStatusFactoryTest extends FunSuite{

  test("getAdminStatus"){

    val status = Map("adminStatusId"->"admin","name"->"Tebogo")

    val adminStatus = AdminStatusFactory.getAdminStatus(status)
    assert(adminStatus==AdminStatus(adminStatusId="admin",name = "Tebogo"))
  }

}

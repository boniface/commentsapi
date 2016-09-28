package factories.util

import java.util.Date

import domain.util.Mail
import org.scalatest.FunSuite

/**
  * Created by Quest on 2016/09/27.
  */
class MailFactoryTest extends FunSuite{

  test("getMail"){
      val values = Map("orgId"->"Masole","id"->"id","key"->"key","value"->"value","host"->"10.18.8.9","port"->"3128","state"->"state")
      val dates = new Date

    val getMail = MailFactory.getMail(values,dates)
    assert(getMail == Mail(orgId = "Masole",id = "id",key = "key",value = "value",
      host = "10.18.8.9",port = "3128",state = "state",dates))
  }

}

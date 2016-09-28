package factories.syslog

import domain.syslog.SystemLogEvents
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by Quest on 2016/09/27.
  */
class SystemLogEventsFactoryTest extends FunSuite{

  test("getSystemLogEvents"){

    val values = Map("orgCode"->"CPUT","id"->"id","eventName"->"Error","eventType"->"404","message"->"Not Found")
    val dates = new DateTime
    val getEvent = SystemLogEventsFactory.getSystemLogEvents(values,dates)
    assert(getEvent == SystemLogEvents(orgCode = "CPUT",id = "id",eventName = "Error",eventType = "404",message = "Not Found",date = dates))
  }

}

package services.syslog

import domain.syslog.SystemLogEvents
import org.joda.time.DateTime
import org.scalatest.FunSuite
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/17.
  */
class SystemLogEventsServiceTest extends FunSuite{

  test("Create and save Event"){
    val event = SystemLogEvents("SANDF","1","404","Http Response error","Page not found", new DateTime)
    val results = Await.result(SystemLogEventsService.apply().save(event), 2.minutes)
    assert(results.wasApplied)
  }

  test("getEventById"){
    val results = Await.result(SystemLogEventsService.apply().getEventById("1"), 2.minutes)
    assert(results.get.eventName === "404")
  }

  test("getEventsByDate"){
    val results = Await.result(SystemLogEventsService.apply().getEventsByDate(new DateTime("12/02/2016")),2.minutes)
    assert(results.get.id === "1")
  }

  test("getEvents"){
    val results = Await.result(SystemLogEventsService.apply().getEvents,2.minutes)
    assert(results.head.id === "1")
  }
  test("deleteById"){
    val  results = Await.result(SystemLogEventsService.apply().deleteById("1"), 2.minutes)
    assert(results.isExhausted)
  }

}

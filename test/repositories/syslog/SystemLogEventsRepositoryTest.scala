package repositories.syslog

import domain.syslog.SystemLogEvents
import org.joda.time.DateTime
import org.scalatest.{FunSpec, GivenWhenThen}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Quest on 2016/11/16.
  */
class SystemLogEventsRepositoryTest extends FunSpec with GivenWhenThen{

  feature("Create SystemLogEvent"){
    info("Create SystemLogEvent")
    scenario("Create SystemLogEvent"){
      Given("orgCode,id: String,eventName: String,eventType: String, message: String, date: DateTime")
      val orgCode = "SANDF"
      val id = "404"
      val eventName = "Http Error"
      val eventType = "Http Error"
      val message = "PAGE NOT FOUND"
      val date = DateTime
      Then("Create Event")
      val event = SystemLogEvents(orgCode,id,eventName,eventType,message,date)
      val repo = SystemLogEventsRepository
      repo.save(event)
      Then("Display all events")
      val displayAll = Await.result(repo.getEvents, 2 minutes)
      displayAll.foreach(i=> println("Events======>",i))
    }
  }
}

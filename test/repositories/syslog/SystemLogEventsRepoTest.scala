package repositories.syslog

import domain.syslog.SystemLogEvents
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen, FunSuite}
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by Quest on 2016/11/20.
  */
class SystemLogEventsRepoTest extends FeatureSpec with GivenWhenThen{

  feature("Create Event") {
    info("Admin add a Event")
    scenario("Admin add new Event ") {
      Given("Given orgCode,id,eventType,eventName,message,date")
      val orgCode = "SANDF"
      val id = "1"
      val eventName = "Http Error"
      val eventType = "Http Error"
      val message = "404 PAGE NOT FOUND"
      val date = new DateTime
      Then("Add Event ")
      val event = SystemLogEvents(orgCode,id,eventName,eventType,message,date)
      val eventRepo = SystemLogEventsRepository
      eventRepo.save(event)
      Then("Display All ")
      val displayAllEvents = Await.result(eventRepo.getEvents, 2 minutes)
      displayAllEvents.foreach(i => println("Events=======>", i))
      val displayIdEvents = Await.result(eventRepo.getEventById(id), 2 minutes)
      displayIdEvents.foreach(i => println("Administrators=======>", i))
    }
  }
}

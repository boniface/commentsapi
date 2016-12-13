package controllers.syslog

import javax.inject.Inject

import controllers.syslog.SystemLogEventsController
import org.joda.time.DateTime
import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Quest on 2016/12/01.
  */
class SystemLogEventsRouter @Inject()(systemEvents:SystemLogEventsController) extends SimpleRouter{

  override def routes: Routes = {
    case GET(p"/get/id") =>
      systemEvents.getEventById("id")
    case GET(p"/get/all") =>
      systemEvents.getAllEvents
    case POST(p"/create") =>
      systemEvents.createOrUpdate
    case DELETE(p"/del/id") =>
      systemEvents.deleteById("id")
    case GET(p"/get/date") =>
      systemEvents.getEventsByDate(new DateTime())
  }
}

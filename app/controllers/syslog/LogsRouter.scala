package controllers.syslog

import javax.inject.Inject

import org.joda.time.DateTime
import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Quest on 2016/12/01.
  */
class LogsRouter @Inject()(systemEvents:SystemLogEventsController) extends SimpleRouter{

  override def routes: Routes = {
    case GET(p"/logs/site/$siteId") =>
      systemEvents.getSiteLogs(siteId)
    case GET(p"/logs/site/$siteId/i$id") =>
      systemEvents.getEventById(siteId, id)

  }
}

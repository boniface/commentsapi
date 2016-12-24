package controllers.syslog

import domain.syslog.SystemLogEvents
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.syslog.SystemLogEventsService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Quest on 2016/12/01.
  */
class SystemLogEventsController extends Controller {

  def getEventById(siteId: String, id: String) = Action.async {
    request =>
      val response = for {
        results <- SystemLogEventsService.apply.getEventById(siteId, id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getSiteLogs(siteId: String) = Action.async {
    request =>
      val response = for {
        results <- SystemLogEventsService.apply.getSiteLogs(siteId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

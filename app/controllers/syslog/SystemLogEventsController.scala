package controllers.syslog

import domain.syslog.SystemLogEvents
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Controller, Action}
import play.api.mvc.BodyParsers.parse
import services.syslog.SystemLogEventsService

/**
  * Created by Quest on 2016/12/01.
  */
class SystemLogEventsController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[SystemLogEvents](input).get
      val response = for {
        results <- SystemLogEventsService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getEventById(id: String) = Action.async {
    request =>
      val response = for {
        results <- SystemLogEventsService.apply.getEventById(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllEvents = Action.async {
    request =>
      val response = for {
        results <- SystemLogEventsService.apply.getEvents
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def deleteById(id:String) = Action.async {
    request =>
      val response = for {
        results <- SystemLogEventsService.apply.deleteById(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getEventsByDate(date: DateTime) = Action.async {
    request =>
      val response = for {
        results <- SystemLogEventsService.apply.getEventsByDate(date)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

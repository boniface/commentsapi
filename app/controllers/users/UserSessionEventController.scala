package controllers.users

import domain.users.UserSessionEvent
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserSessionEventService

/**
 * Created by Rosie on 2016/12/02.
 */
class UserSessionEventController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserSessionEvent](input).get
      val response = for {
        results <- UserSessionEventService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUserSessionEvt(id: String) = Action.async {
    request =>
      val response = for {
        results <- UserSessionEventService.apply.getEventById(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAllUserSessEvt = Action.async {
    request =>
      val response = for {
        results <- UserSessionEventService.apply.getAllSessionEvent
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}

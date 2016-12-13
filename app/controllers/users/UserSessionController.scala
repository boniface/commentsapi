package controllers.users

import domain.users.UserSession
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserSessionService

/**
 * Created by Rosie on 2016/12/02.
 */

class UserSessionController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserSession](input).get
      val response = for {
        results <- UserSessionService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUserSession(sessionId: String) = Action.async {
    request =>
      val response = for {
        results <- UserSessionService.apply.getUserSessionById(sessionId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAllUserSessions = Action.async {
    request =>
      val response = for {
        results <- UserSessionService.apply.getAllUserSessions
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}

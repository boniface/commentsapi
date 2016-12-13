package controllers.users

import domain.users.UserStatus
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserStatusService

/**
 * Created by Rosie on 2016/12/02.
 */
class UsersStatusController  extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserStatus](input).get
      val response = for {
        results <- UserStatusService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUserStatus(user: String) = Action.async {
    request =>
      val response = for {
        results <- UserStatusService.apply.getStatusByUserId(user)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAllUsersStatus = Action.async {
    request =>
      val response = for {
        results <- UserStatusService.apply.getAllUserStatus
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

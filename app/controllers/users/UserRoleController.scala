package controllers.users

import domain.users.UserRole
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserRoleService

/**
 * Created by Rosie on 2016/12/02.
 */
class UserRoleController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserRole](input).get
      val response = for {
        results <- UserRoleService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUserRole(emailId: String) = Action.async {
    request =>
      val response = for {
        results <- UserRoleService.apply.getRoleById(emailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAllUserRoles = Action.async {
    request =>
      val response = for {
        results <- UserRoleService.apply.getAllRoles
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}

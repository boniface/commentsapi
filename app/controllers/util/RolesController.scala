package controllers.util

import domain.util.Roles
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.util.RoleService

/**
  * Created by Quest on 2016/12/05.
  */
class RolesController extends Controller{

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Roles](input).get
      val response = for {
        results <- RoleService.apply.create(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getRole(id: String) = Action.async {
    request =>
      val response = for {
        results <- RoleService.apply.getById(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllRoles = Action.async {
    request =>
      val response = for {
        results <- RoleService.apply.getAll
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

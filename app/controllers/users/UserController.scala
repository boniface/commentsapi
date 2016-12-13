package controllers.users

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import domain.users.User
import services.users.UserService

/**
 * Created by Rosie on 2016/12/02.
 */
class UserController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[User](input).get
      val response = for {
        results <- UserService.apply.createUser(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUser(email: String) = Action.async {
    request =>
      val response = for {
        results <- UserService.apply.getUser(email)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAllUsers = Action.async {
    request =>
      val response = for {
        results <- UserService.apply.getAllUsers
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}

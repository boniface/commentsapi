package controllers.users

import domain.users.UserGeneratedToken
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserGeneratedTokenService

/**
 * Created by Rosie on 2016/12/02.
 */
class UserGeneratedTokenController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserGeneratedToken](input).get
      val response = for {
        results <- UserGeneratedTokenService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUser(token: String) = Action.async {
    request =>
      val response = for {
        results <- UserGeneratedTokenService.apply.getStatusById(token)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAllUserToken = Action.async {
    request =>
      val response = for {
        results <- UserGeneratedTokenService.apply.getAllTokens
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}

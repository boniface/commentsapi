package controllers.util

import conf.security.Credential
import domain.util.Token
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.util.TokenService

/**
  * Created by Quest on 2016/12/05.
  */
class TokenController extends Controller{

  def save = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Token](input).get
      val response = for {
        results <- TokenService.apply().save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def createNewToken(credential: Credential) = Action.async {
    request =>
      val response = for {
        results <- TokenService.apply().createNewToken(credential)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def revokeToken(token:String) = Action.async {
    request =>
      val response = for {
        results <- TokenService.apply().revokeToken(token)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def isTokenValid(token:String,password: String) = Action.async {
    request =>
      val response = for {
        results <- TokenService.apply().isTokenValid(token,password)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def hasTokenExpired(token:String) = Action.async {
    request =>
      val response = for {
        results <- TokenService.apply().hasTokenExpired(token)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getTokenRoles(token: String) = String.valueOf({
    for{
        results<- TokenService.apply().getTokenRoles(token)
        }yield results
  })

  def getEmail(token: String) = String.valueOf({
    for{
      results<- TokenService.apply().getEmail(token)
    }yield results
  })

  def getOrgCode(token: String) = String.valueOf({
    for{
      results<- TokenService.apply().getOrgCode(token)
    }yield results
  })

}
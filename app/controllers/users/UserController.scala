package controllers.users

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import domain.users.{User, UserRole}
import services.users.{UserRoleService, UserService}
import scala.concurrent.ExecutionContext.Implicits.global

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



  def getSiteUser(siteId: String, email: String) = Action.async {
    request =>
      val response = for {
        results <- UserService.apply.getSiteUser(siteId,email)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getSiteUsers(siteId: String) = Action.async {
    request =>
      val response = for {
        results <- UserService.apply.getSiteUsers(siteId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }



  def updateUser= Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[User](input).get
      val response = for {
        results <- UserService.apply.updateUser(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def checkUserAvailability(siteId:String, emaild: String) = Action.async {
    request =>
      val response = for {
        results <- UserService.apply.checkUserAvailability(siteId,emaild)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  // User Role
  def save=Action.async(parse.json) {
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

  def getUserRoles(siteId: String, emailId: String) = Action.async {
    request =>
      val response = for {
        results <- UserRoleService.apply.getUserRoles(siteId,emailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getUserRole(siteId: String, emailId: String) = Action.async {
    request =>
      val response = for {
        results <- UserRoleService.apply.getUserRole(siteId,emailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}

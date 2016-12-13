package controllers.users

import domain.users.UserLogActivities
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.UserLogActivitiesService

/**
 * Created by Rosie on 2016/12/02.
 */
class UserLogActivitiesController  extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserLogActivities](input).get
      val response = for {
        results <- UserLogActivitiesService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getUserLog(id: String) = Action.async {
    request =>
      val response = for {
        results <- UserLogActivitiesService.apply.getLogDetailsById(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAllUserLog = Action.async {
    request =>
      val response = for {
        results <- UserLogActivitiesService.apply.getAllLogActivities
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}

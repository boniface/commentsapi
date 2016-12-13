package controllers.sites

import domain.sites.AdminStatus
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.sites.AdminStatusService

/**
  * Created by Quest on 2016/12/01.
  */
class AdminStatusController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[AdminStatus](input).get
      val response = for {
        results <- AdminStatusService.apply().save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getAdminStatusById(adminStatusId:String) = Action.async {
    request =>
      val response = for {
        results <- AdminStatusService.apply().getAdminStatusById(adminStatusId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def deleteById(adminStatusId:String) = Action.async {
    request =>
      val response = for {
        results <- AdminStatusService.apply().deleteById(adminStatusId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllAdminStatus = Action.async {
    request =>
      val response = for {
        results <- AdminStatusService.apply().getAllAdminStatus
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}

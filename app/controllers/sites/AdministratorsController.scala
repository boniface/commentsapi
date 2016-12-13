package controllers.sites

import domain.sites.Administrators
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.sites.AdministratorsService

/**
  * Created by Quest on 2016/12/01.
  */
class AdministratorsController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Administrators](input).get
      val response = for {
        results <- AdministratorsService.apply().saveAdministrator(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getAdministratorsBySiteId(siteId:String,email:String) = Action.async {
    request =>
      val response = for {
        results <- AdministratorsService.apply().getAdministratorsBySiteId(siteId,email)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def deleteAdministratorBySiteId(siteId:String) = Action.async {
    request =>
      val response = for {
        results <- AdministratorsService.apply().deleteAdministratorBySiteId(siteId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAdministrators = Action.async {
    request =>
      val response = for {
        results <- AdministratorsService.apply().getAdministrators
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}

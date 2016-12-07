package controllers.sites

import domain.sites.AdministratorHistory
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.sites.AdministratorHistoryService

/**
  * Created by Quest on 2016/12/01.
  */

class AdministratorHistoryController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[AdministratorHistory](input).get
      val response = for {
        results <- AdministratorHistoryService.apply().save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getAdministratorHistoryByDate(date: DateTime) = Action.async {
    request =>
      val response = for {
        results <- AdministratorHistoryService.apply().getAdministratorHistoryByDate(date)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def deleteAll = Action.async {
    request =>
      val response = for {
        results <- AdministratorHistoryService.apply().deleteAll
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllAdministratorsHistory = Action.async {
    request =>
      val response = for {
        results <- AdministratorHistoryService.apply().getAllAdministratorsHistory
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

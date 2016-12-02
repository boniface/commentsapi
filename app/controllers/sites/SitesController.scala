package controllers.sites

import domain.sites.Site
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.sites.SitesService

/**
  * Created by hashcode on 2015/11/28.
  */
class SitesController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Site](input).get
      val response = for {
        results <- SitesService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getSite(siteId: String) = Action.async {
    request =>
      val response = for {
        results <- SitesService.apply.getSiteById(siteId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllSites = Action.async {
    request =>
      val response = for {
        results <- SitesService.apply.getAllSites
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

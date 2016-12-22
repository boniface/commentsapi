package controllers.sites

import conf.util.StatusMessages
import domain.sites.Site
import domain.util.ItemStatus
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.sites.SitesService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2015/11/28.
  */
class SitesController extends Controller {

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Site](input).get
      val status = ItemStatus(entity.siteId,new DateTime,StatusMessages.ACTIVE,StatusMessages.CREATED)
      val response = for {
        results <- SitesService.apply.createOrUpdate(entity,status)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def save = Action.async(parse.json) {
    request =>
      val input = request.body

      val entity = Json.fromJson[Site](input).get
      val status = ItemStatus(entity.siteId,new DateTime,StatusMessages.ACTIVE,StatusMessages.UPDATED)
      val response = for {
        results <- SitesService.apply.createOrUpdate(entity,status)
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

  def getSiteStatus(siteId: String) = Action.async {
    request =>
      val response = for {
        results <- SitesService.apply.getSiteById(siteId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getSiteStatusHistory(siteId: String) = Action.async {
    request =>
      val response = for {
        results <- SitesService.apply.getSiteById(siteId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def disableSite(siteId: String) = Action.async {
    request =>
      val status = ItemStatus(siteId,new DateTime,StatusMessages.INACTIVE,StatusMessages.DISABLED)
      val response = for {
        site <- SitesService.apply.getSiteById(siteId)
        results <-SitesService.apply.createOrUpdate(site.get,status)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def activateSite(siteId: String) = Action.async {
    val status = ItemStatus(siteId,new DateTime,StatusMessages.ACTIVE,StatusMessages.ACTIVATED)
    request =>
      val response = for {
        site <- SitesService.apply.getSiteById(siteId)
        results <-SitesService.apply.createOrUpdate(site.get,status)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

package controllers.sites

import conf.util.StatusMessages
import domain.sites.{Administrators, SiteMessages}
import domain.util.ItemStatus
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.sites.{AdministratorsService, SitesService}

/**
  * Created by Quest on 2016/12/01.
  */
class AdministratorsController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Administrators](input).get
      val response = for {
        results <- AdministratorsService.apply.saveAdministrator(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }


  def getSiteAdministrator(siteId: String, emailId: String) = Action.async {
    request =>
      val response = for {
        results <- AdministratorsService.apply.getSiteAdministrator(siteId, emailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getSiteAdministrators(siteId: String) = Action.async {
    request =>
      val response = for {
        results <- AdministratorsService.apply.getSiteAdministrators(siteId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getSiteAdministratorStatus(emailId: String) = Action.async {
    request =>
      val response = for {
        results <- AdministratorsService.apply.getSiteAdministratorStatus(emailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getSiteAdministratorStatusHistory(emailId: String) = Action.async {
    request =>
      val response = for {
        results <- AdministratorsService.apply.getSiteAdministratorStatusHistory(emailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def revokeAdminRights(siteId:String, emailId: String) = Action.async {
    request =>
      val status = ItemStatus(emailId,new DateTime,SiteMessages.REVOKED,SiteMessages.REVOKED)
      val response = for {
        site <- AdministratorsService.apply.getSiteAdministrator(siteId,emailId)
        results <-AdministratorsService.apply.saveAdministratorStatus(status)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def activatedAdministrator(siteId: String, emailId:String) = Action.async {
    val status = ItemStatus(emailId,new DateTime,SiteMessages.ACTIVE,SiteMessages.REINSTATED)
    request =>
      val response = for {
        site <- AdministratorsService.apply.getSiteAdministrator(siteId,emailId)
        results <-AdministratorsService.apply.saveAdministratorStatus(status)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}

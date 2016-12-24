package controllers.util

import domain.util.Mail
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.util.MailService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Quest on 2016/12/05.
  */
class MailController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Mail](input).get
      val response = for {
        results <- MailService.apply().saveOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getMail(orgId:String,id: String) = Action.async {
    request =>
      val response = for {
        results <- MailService.apply().get(orgId,id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllMail(orgId:String) = Action.async {
    request =>
      val response = for {
        results <- MailService.apply().getAll(orgId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getMailer(orgId:String) = Action.async {
    request =>
      val response = for {
        results <- MailService.apply().getMailer(orgId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

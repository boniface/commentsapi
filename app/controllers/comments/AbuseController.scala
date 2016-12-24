package controllers.comments
import domain.comments.Abuse
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.AbuseService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Bonga on 12/1/2016.
  */
class AbuseController extends Controller {


  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Abuse](input).get
      val response = for {
        results <-AbuseService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }


  def getItemAbuse(siteId: String, commentIdOrResponseId: String) = Action.async {
    request =>
      val response = for {
        results <- AbuseService.apply.getItemAbuse(siteId,commentIdOrResponseId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getUserAbusiveComments(siteId: String, emailId: String) = Action.async {
    request =>
      val response = for {
        results <- AbuseService.apply.getUserAbusiveComments(siteId,emailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
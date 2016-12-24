package controllers.comments
import domain.comments.{Response, ResponseStatus}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.{ResponseService, ResponseStatusService}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Bonga on 12/1/2016.
  */
class ResponseController extends Controller {


  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Response](input).get
      val response = for {
        results <- ResponseService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }



  def getCommentResponses(commentId: String) = Action.async {
    request =>
      val response = for {
        results <- ResponseService.apply.getCommentResponses(commentId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getResponse(responseId: String) = Action.async {
    request =>
      val response = for {
        results <- ResponseService.apply.getResponse(responseId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getUserResponses(emailId: String) = Action.async {
    request =>
      val response = for {
        results <- ResponseService.apply.getUserResponses(emailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def saveStatus= Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[ResponseStatus](input).get
      val response = for {
        results <- ResponseStatusService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getResponseStatus(responseId: String) = Action.async {
    request =>
      val response = for {
        results <- ResponseStatusService.apply.getResponseStatus(responseId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}
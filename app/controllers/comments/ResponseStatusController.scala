package controllers.comments
import domain.comments.ResponseStatus
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.ResponseStatusService

/**
  * Created by Bonga on 12/1/2016.
  */
class ResponseStatusController extends Controller {


  def createOrUpdate = Action.async(parse.json) {
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

  def getResponseStatus(subjectId: String) = Action.async {
    request =>
      val response = for {
        results <- ResponseStatusService.apply.getResponseStatusBySubjectId(subjectId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllResponseStatus = Action.async {
    request =>
      val response = for {
        results <- ResponseStatusService.apply.getAllResponseStatus
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}

package controllers.comments
import domain.comments.Response
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.ResponseService

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

  def getResponse(subjectId: String) = Action.async {
    request =>
      val response = for {
        results <- ResponseService.apply.getResponseByCommentId(subjectId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllResponse = Action.async {
    request =>
      val response = for {
        results <- ResponseService.apply.getAllResponse
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}
package controllers.comments
import domain.comments.CommentStatus
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.CommentStatusService

/**
  * Created by Bonga on 12/1/2016.
  */
class CommentStatusController extends Controller {


  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[CommentStatus](input).get
      val response = for {
        results <- CommentStatusService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getCommentStatus(subjectId: String) = Action.async {
    request =>
      val response = for {
        results <- CommentStatusService.apply.getCommentStatusBySubjectId(subjectId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
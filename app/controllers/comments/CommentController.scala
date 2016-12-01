package controllers.comments
import domain.comments.Comment
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.CommentService
/**
  * Created by Bonga on 12/1/2016.
  */
class CommentController extends Controller {


  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Comment](input).get
      val response = for {
        results <- CommentService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getComment(subjectId: String) = Action.async {
    request =>
      val response = for {
        results <- CommentService.apply.getCommentBySubjectId(subjectId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllComment = Action.async {
    request =>
      val response = for {
        results <- CommentService.apply.getAllComment
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}
package controllers.comments
import domain.comments.{Comment, CommentStatus}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.{CommentService, CommentStatusService}
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by Bonga on 12/1/2016.
  */
class CommentController extends Controller {


  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Comment](input).get
      val response = for {
        results <- CommentService.apply.saveComment(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getSiteComments(siteId: String) = Action.async {
    request =>
      val response = for {
        results <- CommentService.apply.getSiteComments(siteId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getSubjectComments(siteId: String, subjectId: String) = Action.async {
    request =>
      val response = for {
        results <- CommentService.apply.getSubjectComments(siteId,subjectId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getComment(siteId: String, subjectId: String, commentId: String) = Action.async {
    request =>
      val response = for {
        results <- CommentService.apply.getComment(siteId,subjectId,commentId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getUserComments(siteId: String, emailId: String) = Action.async {
    request =>
      val response = for {
        results <- CommentService.apply.getUserComments(siteId,emailId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def saveStatus= Action.async(parse.json) {
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

  def getCommentStatus(commentsId: String)= Action.async {
    request =>
      val response = for {
        results <- CommentStatusService.apply.getCommentStatus(commentsId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}
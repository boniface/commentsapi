package controllers.comments
import domain.comments.Subject
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.SubjectService

/**
  * Created by Bonga on 12/1/2016.
  */
class SubjectController extends Controller {


  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Subject](input).get
      val response = for {
        results <- SubjectService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getSubject(subjectId: String) = Action.async {
    request =>
      val response = for {
        results <- SubjectService.apply.getSubjectBySubjectId(subjectId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllSubject = Action.async {
    request =>
      val response = for {
        results <- SubjectService.apply.getAllSubject
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}
package controllers.comments
import domain.comments.Subject
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.SubjectService

import scala.concurrent.ExecutionContext.Implicits.global

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



  def getSubjectById(siteId: String, subjectId: String)= Action.async {
    request =>
      val response = for {
        results <- SubjectService.apply.getSubjectById(siteId,subjectId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getSiteSubjects(siteId: String)= Action.async {
    request =>
      val response = for {
        results <- SubjectService.apply.getSiteSubjects(siteId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
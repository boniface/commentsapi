package controllers.votes

import domain.votes.VoteUp
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.votes.VoteUpService

/**
  * Created by fatimam on 04/12/2016.
  */
class VotesUpController  extends Controller
{
  def saveOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[VoteUp](input).get
      val response = for {
        results <- VoteUpService.apply().saveOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }


  def get(id: String) = Action.async {
    request =>
      val response = for {
        results <-VoteUpService.apply().get(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAll = Action.async {
    request =>
      val response = for {
        results <- VoteUpService.apply().getAll
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}

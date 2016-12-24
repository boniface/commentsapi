package controllers.votes

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.votes.VoteService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/12/24.
  */
class VotesController  extends Controller{

  def get(id: String) = Action.async {
    request =>
      val response = for {
        results <-VoteService.apply.getDownVotes(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }



}

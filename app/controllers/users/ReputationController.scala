package controllers.users

import domain.users.Reputation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.ReputationService

/**
 * Created by Rosie on 2016/12/02.
 */
class ReputationController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Reputation](input).get
      val response = for {
        results <- ReputationService.apply.save(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getReputation(email: String) = Action.async {
    request =>
      val response = for {
        results <- ReputationService.apply.getReputationById(email)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAllReputations = Action.async {
    request =>
      val response = for {
        results <- ReputationService.apply.getAllReputation
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

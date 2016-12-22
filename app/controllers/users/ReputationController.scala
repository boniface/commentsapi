package controllers.users

import com.websudos.phantom.dsl.DateTime
import domain.users.Reputation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.users.ReputationService
import scala.concurrent.ExecutionContext.Implicits.global

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

  def getDayReputation(siteId: String, emailId:String, date:DateTime) = Action.async {
    request =>
      val response = for {
        results <- ReputationService.apply.getDayReputation(siteId,emailId,date)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getReputations(siteId: String, emailId:String) = Action.async {
    request =>
      val response = for {
        results <- ReputationService.apply.getUserReputations(siteId,emailId)
      } yield results
      response.map(ans
      => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

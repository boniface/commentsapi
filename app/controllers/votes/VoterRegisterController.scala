package controllers.votes

import domain.votes.VoterRegister
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.votes.VoterRegisterService

/**
  * Created by fatimam on 04/12/2016.
  */
class VoterRegisterController extends Controller
{
  def saveOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[VoterRegister](input).get
      val response = for {
        results <- VoterRegisterService.apply().saveOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }


  def get(id: String) = Action.async {
    request =>
      val response = for {
        results <-VoterRegisterService.apply().get(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getAll = Action.async {
    request =>
      val response = for {
        results <- VoterRegisterService.apply().getAll
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }





}

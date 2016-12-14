package controllers.util

import domain.util.Keys
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.util.KeyService

/**
  * Created by Quest on 2016/12/05.
  */
class KeysController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Keys](input).get
      val response = for {
        results <- KeyService.apply().saveOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getKey(id: String) = Action.async {
    request =>
      val response = for {
        results <- KeyService.apply().get(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getAllKeys = Action.async {
    request =>
      val response = for {
        results <- KeyService.apply().getAll
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}

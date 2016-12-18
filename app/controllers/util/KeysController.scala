package controllers.util

import conf.security.AuthUtil
import domain.util.Keys
import org.joda.time.DateTime
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
      val accessKey = AuthUtil.encode(entity.id+new DateTime().toString)
      val key = entity.copy(value =accessKey,status = "ACTIVE")
      val response = for {
        results <- KeyService.apply().saveOrUpdate(key)
      } yield results
      response.map(ans => Ok(Json.toJson(key)))
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

  def revokeKey(id: String) = Action.async {
    request =>
      val response = for {
        results <- KeyService.apply().get(id)
        save <- KeyService.apply().saveOrUpdate(results.get.copy(status = "REVOKED"))
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

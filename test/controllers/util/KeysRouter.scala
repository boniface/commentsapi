package controllers.util

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Quest on 2016/12/07.
  */
class KeysRouter @Inject() (keys:KeysController)extends SimpleRouter{

  override def routes: Routes = {
    case POST(p"/create") =>
      keys.createOrUpdate
    case GET(p"/get/all") =>
      keys.getAllKeys
    case GET(p"/get/id") =>
      keys.getKey("id")
  }
}

package controllers.util

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._
/**
  * Created by Quest on 2016/12/07.
  */
class RolesRouter @Inject() (role: RolesController) extends SimpleRouter{

  override def routes: Routes = {
    case POST(p"/create")=>
      role.create
    case GET(p"/get/id")=>
      role.getRole("id")
    case GET(p"/get/all")=>
      role.getAllRoles
  }
}

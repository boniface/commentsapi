package controllers.users

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
 * Created by Rosie on 2016/12/07.
 */
class UserRoleRouter @Inject()
(userRole: UserRoleController)
  extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/get/$emailId") =>
      userRole.getUserRole(emailId)
    case GET(p"/get/all") =>
      userRole.getAllUserRoles
    case POST(p"/create") =>
      userRole.createOrUpdate
  }

}

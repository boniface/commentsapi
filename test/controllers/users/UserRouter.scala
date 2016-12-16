package controllers.users

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
 * Created by Rosie on 2016/12/07.
 */
class UserRouter @Inject()
(users: UserController)
  extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/get/$emailId") =>
      users.getUser(emailId)
    case GET(p"/get/all") =>
      users.getAllUsers
    case POST(p"/create") =>
      users.createOrUpdate
  }

}
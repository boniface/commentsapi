package controllers.users

import java.util.Date
import javax.inject.Inject

import org.joda.time.DateTime
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
 * Created by Rosie on 2016/12/15.
 */
class UsersRouter @Inject()
(reputationController: ReputationController)
(userController: UserController)
(usersStatusController: UsersStatusController)

  extends SimpleRouter{

  override def routes: Routes = {

    //Reputation
    case POST(p"/reputation/create") =>
      reputationController.createOrUpdate
    case GET(p"/reputation/get/$siteId/$emailId/$date}") =>
      reputationController.getDayReputation(siteId,emailId,DateTime.parse(date))
    case GET(p"/reputation/all/$siteId/$emailId") =>
      reputationController.getReputations(siteId,emailId)

    //User
    case POST (p"/user/create") =>
      userController.createOrUpdate
    case POST (p"/user/update") =>
      userController.updateUser
    case GET (p"/user/get/$siteId/$email")=>
      userController.getSiteUser(siteId,email)
    case GET(p"/user/all/$siteId")=>
      userController.getSiteUsers(siteId)
    case GET (p"/user/get/$siteId/$email")=>
      userController.checkUserAvailability(siteId,email)

    //UserRole
    case POST (p"/userRole/create")=>
      userController.save
    case GET(p"/userRole/get/$siteId/$emailId")=>
      userController.getUserRole(siteId,emailId)
    case GET(p"/userRole/all/$siteId/$emailId")=>
      userController.getUserRoles(siteId,emailId)

    //UserStatus
    case POST (p"/userStatus/create")=>
      usersStatusController.createOrUpdate
    case GET (p"/userStatus/get/$user")=>
      usersStatusController.getUserStatus(user)
    case GET (p"/userStatus/all")=>
      usersStatusController.getAllUsersStatus
  }

}

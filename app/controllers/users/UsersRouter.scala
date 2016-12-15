package controllers.users

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
 * Created by Rosie on 2016/12/15.
 */
class UsersRouter @Inject()
(reputationController: ReputationController)
(userController: UserController)
(userGeneratedTokenController: UserGeneratedTokenController)
(userLogActivitiesController: UserLogActivitiesController)
(userRoleController: UserRoleController)
(userSessionController: UserSessionController)
(userSessionEventController: UserSessionEventController)
(usersStatusController: UsersStatusController)

  extends SimpleRouter{

  override def routes: Routes = {

    //Reputation
    case POST(p"/reputation/create") =>
      reputationController.createOrUpdate
    case GET(p"/reputation/get/$email") =>
      reputationController.getReputation(email)
    case GET(p"/reputation/all") =>
      reputationController.getAllReputations

    //User
    case POST (p"/user/create") =>
      userController.createOrUpdate
    case GET (p"/user/get/$email")=>
      userController.getUser(email)
    case GET(p"/user/all")=>
      userController.getAllUsers

    //UserGeneratedToken
    case POST(p"/userGeneratedToken/create")=>
      userGeneratedTokenController.createOrUpdate
    case GET (p"/userGeneratedToken/get/$token")=>
      userGeneratedTokenController.getUser(token)
    case GET(p"/userGeneratedToken/all")=>
      userGeneratedTokenController.getAllUserToken

    //UserLogActivities
    case POST (p"/userLogActivities/create")=>
      userLogActivitiesController.createOrUpdate
    case GET (p"/userLogActivities/get/$id")=>
      userLogActivitiesController.getUserLog(id)
    case GET(p"/userLogActivities/all")=>
      userLogActivitiesController.getAllUserLog

    //UserRole
    case POST (p"/userRole/create")=>
      userRoleController.createOrUpdate
    case GET(p"/userRole/get/$emailId")=>
      userRoleController.getUserRole(emailId)
    case GET(p"/userRole/all")=>
      userRoleController.getAllUserRoles

    //UserSession
    case POST(p"/userSession/creation") =>
      userSessionController.createOrUpdate
    case GET(p"/userSession/get/$sessionId")=>
      userSessionController.getUserSession(sessionId)
    case GET(p"/userSession/all")=>
      userSessionController.getAllUserSessions

    //UserSessionEvent
    case POST(p"/userSessionEvent/create") =>
      userSessionEventController.createOrUpdate
    case GET(p"/userSessionEvent/get/$id")=>
      userSessionEventController.getUserSessionEvt(id)
    case GET(p"/userSessionEvent/all")=>
      userSessionEventController.getAllUserSessEvt

    //UserStatus
    case POST (p"/userStatus/create")=>
      usersStatusController.createOrUpdate
    case GET (p"/userStatus/get/$user")=>
      usersStatusController.getUserStatus(user)
    case GET (p"/userStatus/all")=>
      usersStatusController.getAllUsersStatus
  }

}

package conf.security

import domain.users.User
import services.users.UserService
import services.util.TokenService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/06.
  */
object LoggedInUser {
  def user(token: String): Future[Option[User]] = {
    val email = TokenService.apply().getEmail(token)
    val siteId = TokenService.apply().getOrgCode(token)
    UserService.apply.getSiteUser(siteId, email)
  }
}

package services.users

import com.websudos.phantom.dsl._
import domain.users.UserRole
import services.users.Impl.UserRoleServiceImpl

import scala.concurrent.Future

/**
  * Created by Rosie on 2016/11/28.
  */
trait UserRoleService {
  def save(role: UserRole): Future[ResultSet]

  def getUserRoles(siteId: String, emailId: String): Future[Seq[UserRole]]

  def getUserRole(siteId: String, emailId: String): Future[Option[UserRole]]
}

object UserRoleService {
  def apply(): UserRoleService = new UserRoleServiceImpl
}

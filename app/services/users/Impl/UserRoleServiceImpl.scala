package services.users.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserRole
import repositories.users.UserRoleRepository
import services.Service
import services.users.UserRoleService
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
class UserRoleServiceImpl extends UserRoleService with Service {
  override def save(role: UserRole): Future[ResultSet] = {
    UserRoleRepository.save(role)
  }

  override def getUserRoles(siteId: String, emailId: String): Future[Seq[UserRole]] = {
    UserRoleRepository.getRoleByEmailId(siteId,emailId)
  }

  override def getUserRole(siteId: String, emailId: String): Future[UserRole] = {
    UserRoleRepository.getRoleByEmailId(siteId,emailId) map ( role => role.head)
  }
}

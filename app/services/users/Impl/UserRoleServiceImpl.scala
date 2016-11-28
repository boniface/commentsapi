package services.users.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserRole
import repositories.users.UserRoleRepository
import services.Service
import services.users.UserRoleService

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
class UserRoleServiceImpl extends UserRoleService with Service {
  override def save(emailId: UserRole): Future[ResultSet] = {
    val roleService = UserRole(emailId.emailId, emailId.roleId, emailId.date)
    for{
      result <-UserRoleRepository.save(emailId)
    }yield result
  }

  override def getRoleById(emailId: String): Future[Option[UserRole]]  = {
    //AdminStatusRepository.getAdminStatusById(adminStatusId)
    UserRoleRepository.getRoleByEmailId(emailId)
  }

  override def getAllRoles: Future[Seq[UserRole]] = {
    UserRoleRepository.getRoleId
  }

  override def deleteById(emailId: String): Future[ResultSet] = {
    UserRoleRepository.deleteById(emailId)
  }
}

package services.users

import com.websudos.phantom.dsl._
import domain.users.UserRole
import services.Service
import services.users.Impl.UserRoleServiceImpl

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
trait UserRoleService {
  def save(emailId: UserRole):Future[ResultSet]
  def getRoleById(adminStatusId:String):Future[Option[UserRole]]
  def deleteById(adminStatusId: String):Future[ResultSet]
  def getAllRoles: Future[Seq[UserRole]]

}

object UserRoleService extends Service {
  def apply(): UserRoleService = new UserRoleServiceImpl
}

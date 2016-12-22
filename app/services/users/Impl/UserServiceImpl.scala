package services.users.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.{User, UserRole}
import domain.util.RoleValues
import org.joda.time.DateTime
import repositories.users.{UserRepository, UserRoleRepository}
import services.Service
import services.users.{UserRoleService, UserService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
  * Created by hashcode on 2016/09/04.
  */
class UserServiceImpl extends UserService with Service {

  override def getSiteUser(siteId: String, email: String): Future[Option[User]] = {
    UserRepository.getSiteUser(siteId, email)
  }

  override def getSiteUsers(siteId: String): Future[Seq[User]] = {
    UserRepository.getSiteUsers(siteId)
  }

  override def createUser(user: User): Future[ResultSet] = {
    val anonymous = UserRole(user.siteId,user.email,RoleValues.ANONYMOUS,new DateTime)
    for{
      createUser <- UserRepository.save(user)
      createRole <- UserRoleRepository.save(anonymous)
    } yield createUser
  }

  override def updateUser(user: User): Future[ResultSet] = {
    UserRepository.save(user)
  }

  override def checkUserAvailability(siteId: String,emailId:String): Future[Boolean] = {
    UserRepository.getSiteUser(siteId, emailId) map (result =>
      result match {
        case Some(x) => true
        case None => false
      }
      )
  }

  // User Role
  override def save(role: UserRole): Future[ResultSet] = {
    UserRoleService.apply.save(role)
  }

  override def getUserRoles(siteId: String, emailId: String): Future[Seq[UserRole]] = {
    UserRoleService.apply.getUserRoles(siteId,emailId)
  }

  override def getUserRole(siteId: String, emailId: String): Future[Option[UserRole]] = {
    UserRoleService.apply.getUserRole(siteId,emailId)
  }
}

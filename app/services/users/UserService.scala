package services.users

import com.websudos.phantom.dsl
import com.websudos.phantom.dsl._
import domain.users.{User, UserRole}
import services.Service
import services.users.Impl.UserServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/08/28.
  */
trait UserService {

  def getSiteUser(siteId: String, email: String): Future[Option[User]]

  def getSiteUsers(siteId: String): Future[Seq[User]]

  def createUser(user: User): Future[dsl.ResultSet]

  def updateUser(user: User): Future[dsl.ResultSet]

  def checkUserAvailability(siteId:String, emaild: String): Future[Boolean]

  // User Role
  def save(role: UserRole): Future[ResultSet]

  def getUserRoles(siteId: String, emailId: String): Future[Seq[UserRole]]

  def getUserRole(siteId: String, emailId: String): Future[Option[UserRole]]

}


object UserService{
  def apply: UserService = new UserServiceImpl
}


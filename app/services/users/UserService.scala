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
  def getOrganisationUsers(orgCode: String): Future[Seq[User]]

  def getUser(email: String): Future[Seq[User]]

  def createUser(user: User): Future[dsl.ResultSet]

  def updateUser(user: User): Future[dsl.ResultSet]

  def getUserByEmail(orgCode: String, email: String): Future[Option[User]]

  def checkUserAvailability(user: User): Future[Boolean]

  // User Role
  def saveUserRole(userrole: UserRole): Future[ResultSet]

  def getUserRole(orgCode: String, email: String): Future[Seq[UserRole]]


}


object UserService extends Service {
  def apply(): UserService = new UserServiceImpl
}


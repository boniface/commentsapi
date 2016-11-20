package services.users.Impl

import com.websudos.phantom.dsl
import com.websudos.phantom.dsl.ResultSet
import conf.security.{AuthUtil, RolesID}
import domain.users.{User, UserRole}
import org.joda.time.DateTime
import repositories.users.UserRepository
import services.Service
import services.users.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
  * Created by hashcode on 2016/09/04.
  */
class UserServiceImpl extends UserService with Service {
  override def createUser(user: User): Future[dsl.ResultSet] = {
    val role = UserRole(user.email, RolesID.ANONYMOUS_USER,new DateTime())
    for {
      available <- checkUserAvailability(user: User)
      result <- UserRepository.save(user.copy(password = AuthUtil.encode(user.password))) if !available
    } yield result
  }

  override def checkUserAvailability(user: User): Future[Boolean] = {
    UserRepository.getSiteUser(user.siteId,user.email) map {
      case Some(x) => true
      case _ => false
    }
  }

  override def getUserRole(orgCode:String,email:String): Future[Seq[UserRole]] = ???


  override def getUserByEmail(orgCode:String,email:String): Future[Option[User]] = {
    UserRepository.getSiteUser(orgCode,email)
  }

  override def updateUser(user: User): Future[ResultSet] = {
    UserRepository.save(user)
  }

  override def saveUserRole(userrole: UserRole): Future[ResultSet] = ???


  override def getOrganisationUsers(orgCode: String): Future[Seq[User]] = ???

  override def getUser(email: String): Future[Seq[User]] = ???
}

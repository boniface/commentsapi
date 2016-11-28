package services.users

import com.websudos.phantom.dsl._
import domain.users.UserSession
import services.Service
import services.users.Impl.UserSessionServiceImpl

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
trait UserSessionService {
  def save(sessionId: UserSession):Future[ResultSet]
  def getUserSessionById(sessionId:String):Future[Option[UserSession]]
  def deleteById(adminStatusId: String):Future[ResultSet]
  def getAllUserSessions: Future[Seq[UserSession]]

}
object UserSessionService extends Service{
  def apply(): UserSessionService = new UserSessionServiceImpl
}

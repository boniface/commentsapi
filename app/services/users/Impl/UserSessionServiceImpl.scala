package services.users.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserSession
import repositories.users.UserSessionRepository
import services.users.UserSessionService
import services.Service

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
class UserSessionServiceImpl extends UserSessionService with Service{
  override def save(sessionId: UserSession): Future[ResultSet] = {
    val sessionService = UserSession(sessionId.email, sessionId.userSessionId, sessionId.startTime, sessionId.ipaddress, sessionId.browserSession,sessionId.status, sessionId.tokenId)
    for{
      result <-UserSessionRepository.save(sessionId)
    }yield result
  }

  override def getUserSessionById(sessionId: String): Future[Option[UserSession]] =  {
    UserSessionRepository.getUserSessionById(sessionId)
  }

  override def deleteById(sessionId: String): Future[ResultSet] = {
    UserSessionRepository.deleteById(sessionId)
  }

  override def getAllUserSessions: Future[Seq[UserSession]] = {
    UserSessionRepository.getAllUserSession
  }
}

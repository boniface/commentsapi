package services.users.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserSessionEvent
import repositories.users.UserSessionEventRepository
import services.users.UserSessionEventService
import services.Service

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
class UserSessionEventServiceImpl extends UserSessionEventService with Service{
  override def save(id: UserSessionEvent): Future[ResultSet] = {
    val sessionEventService = UserSessionEvent(id.sessionId, id.id, id.eventTime, id.eventName)
    for{
      result <-UserSessionEventRepository.save(id)
    }yield result
  }

  override def getEventById(id: String): Future[Option[UserSessionEvent]]  = {
   UserSessionEventRepository.getEventById(id)
  }

  override def deleteById(id: String): Future[ResultSet] = {
    UserSessionEventRepository.deleteById(id)
  }

  override def getAllSessionEvent: Future[Seq[UserSessionEvent]] =  {
    UserSessionEventRepository.getAllSessionEvent
  }
}

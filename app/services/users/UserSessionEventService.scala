package services.users

import com.websudos.phantom.dsl._
import domain.users.UserSessionEvent
import services.Service
import services.users.Impl.UserSessionEventServiceImpl
import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
trait UserSessionEventService {
  def save(id: UserSessionEvent):Future[ResultSet]
  def getEventById(id:String):Future[Option[UserSessionEvent]]
  def deleteById(id: String):Future[ResultSet]
  def getAllSessionEvent: Future[Seq[UserSessionEvent]]

}

object UserSessionEventService extends Service{
  def apply():UserSessionEventService = new UserSessionEventServiceImpl
}

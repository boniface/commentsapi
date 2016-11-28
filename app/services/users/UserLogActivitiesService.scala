package services.users
import com.websudos.phantom.dsl._
import domain.users.UserLogActivities
import services.users.Impl.UserLogActivityServiceImpl

import scala.concurrent.Future
import services.Service

/**
 * Created by Rosie on 2016/11/28.
 */
trait UserLogActivitiesService {
  def save(id: UserLogActivities):Future[ResultSet]
  def getLogDetailsById(id:String):Future[Option[UserLogActivities]]
  def deleteById(id: String):Future[ResultSet]
  def getAllLogActivities: Future[Seq[UserLogActivities]]

}

object UserLogActivitiesService extends Service {
  //def apply():AdminStatusService = new AdminStatusServiceImpl
  def apply(): UserLogActivitiesService = new UserLogActivityServiceImpl
}

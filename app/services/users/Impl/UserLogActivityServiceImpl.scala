package services.users.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserLogActivities
import repositories.users.UserLogActivitiesRepository
import services.users.UserLogActivitiesService
import services.Service

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
class UserLogActivityServiceImpl  extends UserLogActivitiesService with Service {
  override def save(id: UserLogActivities): Future[ResultSet] =  {
    val logService = UserLogActivities(id.id, id.emailId,id.sessionId, id.details, id.date,id.description)
    for{
      result <-UserLogActivitiesRepository.save(logService)
    }yield result
  }

  override def getAllLogActivities: Future[Seq[UserLogActivities]] = {
UserLogActivitiesRepository.getAllLogActivities  }

  override def deleteById(id: String): Future[ResultSet] =  {
    UserLogActivitiesRepository.deleteById(id)
  }

  override def getLogDetailsById(id: String): Future[Option[UserLogActivities]] =  {
    UserLogActivitiesRepository.getLogDetails(id)
  }
}

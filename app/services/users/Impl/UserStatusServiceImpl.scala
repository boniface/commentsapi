package services.users.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserStatus
import repositories.users.UserStatusRepository
import services.users.UserStatusService
import services.Service

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
class UserStatusServiceImpl extends UserStatusService with Service{
  override def save(userId: UserStatus): Future[ResultSet] = {
    val userStatusService = UserStatus(userId.siteId,userId.userId,userId.status, userId.date)
    for{
      result <-UserStatusRepository.save(userId)
    }yield result
  }

  override def deleteById(userId: String): Future[ResultSet] =  {
   UserStatusRepository.deleteById(userId)
  }

  override def getStatusByUserId(userId: String): Future[Option[UserStatus]] =  {
    UserStatusRepository.getStatusByUserId(userId)
  }

  override def getAllUserStatus: Future[Seq[UserStatus]] = {
    UserStatusRepository.getAllUserStatus
  }
}

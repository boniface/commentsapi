package services.users

import com.websudos.phantom.dsl._
import domain.users.UserStatus
import services.Service
import services.users.Impl.UserStatusServiceImpl
import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
trait UserStatusService {
  def save(userId: UserStatus):Future[ResultSet]
  def getStatusByUserId(userId:String):Future[Option[UserStatus]]
  def deleteById(userId: String):Future[ResultSet]
  def getAllUserStatus: Future[Seq[UserStatus]]

}

object UserStatusService extends Service {

  def apply(): UserStatusService = new UserStatusServiceImpl
}
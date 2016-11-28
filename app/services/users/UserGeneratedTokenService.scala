package services.users

import com.websudos.phantom.dsl._
import domain.users.UserGeneratedToken
import services.Service
import services.users.Impl.UserGeneratedTokenServiceImpl

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/14.
 */
trait UserGeneratedTokenService {

  def save(token: UserGeneratedToken):Future[ResultSet]
  def getStatusById(adminStatusId:String):Future[Option[UserGeneratedToken]]
  def deleteById(tokenId: String):Future[ResultSet]
  def getAllTokens: Future[Seq[UserGeneratedToken]]
}

object UserGeneratedTokenService extends Service {
  //def apply():AdminStatusService = new AdminStatusServiceImpl
  def apply(): UserGeneratedTokenService = new UserGeneratedTokenServiceImpl
}

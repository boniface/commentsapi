package services.users.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserGeneratedToken
import repositories.users.UserGeneratedTokenRepository
import services.Service
import services.users.UserGeneratedTokenService

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/14.
 */
class UserGeneratedTokenServiceImpl extends UserGeneratedTokenService with Service {
  override def save(token: UserGeneratedToken): Future[ResultSet]   = {
    val tokenService = UserGeneratedToken(token.token, token.siteId, token.message,token.status)
    for{
      result <- UserGeneratedTokenRepository.save(token)
    }yield result
  }
  override def getAllTokens: Future[Seq[UserGeneratedToken]] = {
   UserGeneratedTokenRepository.getAllTokens
  }

  override def getStatusById(token: String): Future[Option[UserGeneratedToken]] =  {
    UserGeneratedTokenRepository.getStatusBySiteId(token)
  }

  override def deleteById(tokenId: String): Future[ResultSet] = {
    UserGeneratedTokenRepository.deleteById(tokenId)
  }
}

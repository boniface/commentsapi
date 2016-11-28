package services.users

import com.websudos.phantom.dsl._
import domain.users.Reputation
import services.Service
import services.users.Impl.ReputationServiceImpl

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
trait ReputationService {

  def save(reputation: Reputation):Future[ResultSet]
  def getReputationById(reputationId:String):Future[Option[Reputation]]
  def deleteById(reputationId: String):Future[ResultSet]
  def getAllReputation: Future[Seq[Reputation]]
}

object ReputationService extends Service {
  def apply():ReputationService = new ReputationServiceImpl

}
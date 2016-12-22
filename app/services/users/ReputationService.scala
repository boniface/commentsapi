package services.users

import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams.Iteratee
import domain.users.Reputation
import repositories.users.ReputationRepository.select
import services.Service
import services.users.Impl.ReputationServiceImpl

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
trait ReputationService {

  def save(reputation: Reputation):Future[ResultSet]
  def getDayReputation(siteId: String, emailId:String, date:DateTime): Future[Option[Reputation]]
  def getUserReputations(siteId: String, emailId:String): Future[Seq[Reputation]]
}

object ReputationService extends Service {
  def apply():ReputationService = new ReputationServiceImpl

}
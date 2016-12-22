package services.users.Impl

import com.websudos.phantom.dsl.{DateTime, ResultSet}
import domain.users.Reputation
import repositories.users.ReputationRepository
import services.users.ReputationService
import services.Service

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
class ReputationServiceImpl extends ReputationService with Service{
  override def save(reputation: Reputation): Future[ResultSet] = {
    ReputationRepository.save(reputation)
  }

  override def getDayReputation(siteId: String, emailId: String, date: DateTime): Future[Option[Reputation]] = {
    ReputationRepository.getDayReputation(siteId,emailId,date)
  }

  override def getUserReputations(siteId: String, emailId: String): Future[Seq[Reputation]] = {
    ReputationRepository.getUserReputations(siteId,emailId)
  }
}

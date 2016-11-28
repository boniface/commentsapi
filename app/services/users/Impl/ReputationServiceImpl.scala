package services.users.Impl

import com.websudos.phantom.dsl.ResultSet
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
    val reputationService = Reputation(reputation.emailId, reputation.date, reputation.value)
    for{
      result <-ReputationRepository.save(reputation)
    }yield result
  }

  override def deleteById(emailId: String): Future[ResultSet] = {
    ReputationRepository.deleteById(emailId)
  }

  override def getReputationById(emailId: String): Future[Option[Reputation]] = {
    ReputationRepository.getReputationByEmailId(emailId)
  }

  override def getAllReputation: Future[Seq[Reputation]] = {
    ReputationRepository.getAllReputations
  }
}

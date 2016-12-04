package services.votes


import com.datastax.driver.core.ResultSet
import domain.votes.VoteUp
import services.votes.impl.VoteUpServiceImpl

import scala.concurrent.Future

/**
  * Created by fatimam on 12/11/2016.
  */
trait VoteUpService
{
  def saveOrUpdate(entity: VoteUp): Future[ResultSet]

  def get(id: String): Future[Option[VoteUp]]

  def getAll: Future[Seq[VoteUp]]

}

object VoteUpService{
  def apply(): VoteUpService = new VoteUpServiceImpl

}
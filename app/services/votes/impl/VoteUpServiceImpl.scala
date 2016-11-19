package services.votes.impl

import com.datastax.driver.core.ResultSet
import domain.votes.VoteUp
import repositories.votes.VoteUpRepository
import services.Service
import services.votes.services.votes.VoteUpService

import scala.concurrent.Future

/**
  * Created by fatimam on 12/11/2016.
  */
class VoteUpServiceImpl extends VoteUpService with Service{

  def saveOrUpdate(entity: VoteUp): Future[ResultSet] = {
    VoteUpRepository.save(entity)
  }
  def get(id:String):Future[Option[VoteUp]] ={
    VoteUpRepository.getVoteUpById(id)
  }

  def getAll:Future[Seq[VoteUp]] ={
    VoteUpRepository.getAllkeys
  }

}

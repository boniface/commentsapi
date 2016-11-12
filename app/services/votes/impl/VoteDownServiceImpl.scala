package services.votes.impl

import com.datastax.driver.core.ResultSet
import domain.votes.VoteDown
import repositories.votes.VoteDownRepository
import services.Service
import services.votes.VoteDownService

import scala.concurrent.Future


/**
  * Created by fatimam on 12/11/2016.
  */
class VoteDownServiceImpl extends VoteDownService with Service{

  def saveOrUpdate(entity: VoteDown): Future[ResultSet] = {
    VoteDownRepository.save(entity)
  }
  def get(id:String):Future[Option[VoteDown]] ={
    VoteDownRepository.getVoteDownById(id)
  }

  def getAll:Future[Seq[VoteDown]] ={
    VoteDownRepository.getAllkeys
  }

}

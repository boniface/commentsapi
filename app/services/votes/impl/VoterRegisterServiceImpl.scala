package services.votes.impl

import com.datastax.driver.core.ResultSet
import domain.votes.VoterRegister
import repositories.Votes.VoterRegisterRepository
import services.Service
import services.votes.VoterRegisterService
import scala.concurrent.Future

/**
  * Created by fatimam on 12/11/2016.
  */
class VoterRegisterServiceImpl extends VoterRegisterService with Service{

  def saveOrUpdate(entity: VoterRegister): Future[ResultSet] = {
    VoterRegisterRepository.save(entity)
  }
  def get(id:String):Future[Option[VoterRegister]] ={
    VoterRegisterRepository.getVoterRegisterById(id)
  }

  def getAll:Future[Seq[VoterRegister]] ={
    VoterRegisterRepository.getAllkeys
  }

}
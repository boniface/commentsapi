package services.votes
import com.datastax.driver.core.ResultSet
import domain.votes.VoterRegister
import services.votes.impl.VoterRegisterServiceImpl

import scala.concurrent.Future

/**
  * Created by fatimam on 12/11/2016.
  */
trait VoterRegisterService
{
  def saveOrUpdate(entity: VoterRegister): Future[ResultSet]

  def get(id: String): Future[Option[VoterRegister]]

  def getAll: Future[Seq[VoterRegister]]

}
object VoterRegisterService
{
  def apply(): VoterRegisterService = new VoterRegisterServiceImpl

}
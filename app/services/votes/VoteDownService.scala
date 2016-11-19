package services.votes
import com.datastax.driver.core.ResultSet
import scala.concurrent.Future
import domain.votes.VoteDown

/**
  * Created by fatimam on 12/11/2016.
  */
trait VoteDownService {

  def saveOrUpdate(entity: VoteDown): Future[ResultSet]

  def get(id: String): Future[Option[VoteDown]]

  def getAll: Future[Seq[VoteDown]]

}

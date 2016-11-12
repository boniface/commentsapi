package repositories.votes

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.votes.VoteUp
import scala.concurrent.Future

/**
  * Created by fatimam on 12/11/2016.
  */
sealed class VoteUpRepository extends CassandraTable[VoteUpRepository, VoteUp] {

  object commentIdOrResponseId extends StringColumn(this) with PartitionKey[String]
  object emailId extends StringColumn(this)
  object ipaddress extends StringColumn(this)
  object count extends IntColumn(this)

  override def fromRow(row: Row): VoteUp = {
    VoteUp(
      commentIdOrResponseId(row),
      emailId(row),
      ipaddress(row),
      count(row)
    )
  }
}

object VoteUpRepository extends VoteUpRepository with RootConnector {
  override lazy val tableName = "voteupkeys"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(voteup: VoteUp): Future[ResultSet] = {
    insert
      .value(_.commentIdOrResponseId, voteup.commentIdOrResponseId)
      .value(_.emailId, voteup.emailId)
      .value(_.ipaddress, voteup.ipaddress)
      .value(_.count, voteup.count)
      .future()
  }
  def getVoteUpById(commentIdOrResponseId: String): Future[Option[VoteUp]] = {
    select.where(_.commentIdOrResponseId eqs commentIdOrResponseId).one()
  }
  def getAllkeys: Future[Seq[VoteUp]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

}




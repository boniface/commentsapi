package repositories.votes

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.votes.VoteDown
import scala.concurrent.Future

/**
  * Created by fatimam on 12/11/2016.
  * commentIdOrResponseId:String,emailId:String,ipaddress:String,count:Int

  */

sealed class VoteDownRepository extends CassandraTable[VoteDownRepository, VoteDown] {

  object commentIdOrResponseId extends StringColumn(this) with PartitionKey[String]
  object emailId extends StringColumn(this)
  object ipaddress extends StringColumn(this)
  object count extends IntColumn(this)

  override def fromRow(row: Row): VoteDown = {
    VoteDown(
      commentIdOrResponseId(row),
      emailId(row),
      ipaddress(row),
      count(row)
    )
  }
}

object VoteDownRepository extends VoteDownRepository with RootConnector {
  override lazy val tableName = "votedownkeys"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(votedown: VoteDown): Future[ResultSet] = {
    insert
      .value(_.commentIdOrResponseId, votedown.commentIdOrResponseId)
      .value(_.emailId, votedown.emailId)
      .value(_.ipaddress, votedown.ipaddress)
      .value(_.count, votedown.count)
      .future()
  }
  def getVoteDownById(commentIdOrResponseId: String): Future[Option[VoteDown]] = {
    select.where(_.commentIdOrResponseId eqs commentIdOrResponseId).one()
  }
  def getAllkeys: Future[Seq[VoteDown]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

}

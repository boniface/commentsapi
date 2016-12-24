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
  * Created by hashcode on 2016/12/24.
  */
class UserDownVotesRepository extends CassandraTable[UserDownVotesRepository, VoteDown] {

  object itemOwnerId extends StringColumn(this) with  PartitionKey[String]

  object itemId extends StringColumn(this) with PrimaryKey[String]

  object ipAddress extends StringColumn(this) with PrimaryKey[String]

  override def fromRow(row: Row): VoteDown = {
    VoteDown(
      itemId(row),
      ipAddress(row),
      itemOwnerId(row)
    )
  }
}

object UserDownVotesRepository extends UserDownVotesRepository with RootConnector {
  override lazy val tableName = "userdownvotes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(votedown: VoteDown): Future[ResultSet] = {
    insert
      .value(_.itemId, votedown.itemId)
      .value(_.ipAddress, votedown.ipAddress)
      .value(_.itemOwnerId, votedown.itemOwnerId)
      .future()
  }

  def getVoteId(itemId: String, ipAddress: String): Future[Option[VoteDown]] = {
    select
      .where(_.itemId eqs itemId)
      .and(_.ipAddress eqs ipAddress)
      .one()
  }

  def getVotes(itemId: String): Future[Seq[VoteDown]] = {
    select
      .where(_.itemId eqs itemId)
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteVote(itemId: String, ipAddress: String): Future[ResultSet] = {
    delete
      .where(_.itemId eqs itemId)
      .and(_.ipAddress eqs ipAddress)
      .future()
  }

}

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
  * itemId:String,ipAddress:String,itemOwnerId:String
  *
  */

sealed class VoteDownRepository extends CassandraTable[VoteDownRepository, VoteDown] {

  object itemId extends StringColumn(this) with PartitionKey[String]

  object ipAddress extends StringColumn(this) with PrimaryKey[String]

  object itemOwnerId extends StringColumn(this)

  object date extends DateTimeColumn(this)

  override def fromRow(row: Row): VoteDown = {
    VoteDown(
      itemId(row),
      ipAddress(row),
      itemOwnerId(row),
      date(row)
    )
  }
}

object VoteDownRepository extends VoteDownRepository with RootConnector {
  override lazy val tableName = "downvotes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(vote: VoteDown): Future[ResultSet] = {
    insert
      .value(_.itemId, vote.itemId)
      .value(_.ipAddress, vote.ipAddress)
      .value(_.itemOwnerId, vote.itemOwnerId)
      .value(_.date, vote.date)
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

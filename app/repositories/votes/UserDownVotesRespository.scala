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

  object siteId extends StringColumn(this) with PartitionKey[String]

  object itemOwnerId extends StringColumn(this) with PartitionKey[String]

  object itemId extends StringColumn(this) with PrimaryKey[String]

  object ipAddress extends StringColumn(this) with PrimaryKey[String]

  object date extends DateTimeColumn(this)

  override def fromRow(row: Row): VoteDown = {
    VoteDown(
      siteId(row),
      itemId(row),
      ipAddress(row),
      itemOwnerId(row),
      date(row)
    )
  }
}

object UserDownVotesRepository extends UserDownVotesRepository with RootConnector {
  override lazy val tableName = "userdownvotes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(vote: VoteDown): Future[ResultSet] = {
    insert
      .value(_.siteId, vote.siteId)
      .value(_.itemId, vote.itemId)
      .value(_.ipAddress, vote.ipAddress)
      .value(_.itemOwnerId, vote.itemOwnerId)
      .value(_.date, vote.date)
      .future()
  }


  def getUserVotes(siteId:String,itemOwnerId: String): Future[Seq[VoteDown]] = {
    select
      .where(_.siteId eqs siteId)
      .and(_.itemOwnerId eqs itemOwnerId)
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteVote(siteId:String,itemOwnerId:String,itemId: String, ipAddress: String): Future[ResultSet] = {
    delete
      .where(_.siteId eqs siteId)
      .and(_.itemOwnerId eqs itemOwnerId)
      .and(_.itemId eqs itemId)
      .and(_.ipAddress eqs ipAddress)
      .future()
  }

}

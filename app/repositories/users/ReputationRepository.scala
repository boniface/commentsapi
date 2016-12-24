package repositories.users

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PrimaryKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import conf.connection.DataConnection._
import domain.users.Reputation

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/28.
 */
class ReputationRepository extends CassandraTable[ReputationRepository, Reputation]{

  object siteId extends StringColumn(this) with PartitionKey[String]
  object emailId extends StringColumn(this) with PrimaryKey[String]
  object date extends DateTimeColumn(this) with PrimaryKey[DateTime]
  object value extends IntColumn(this)

  override def fromRow(r:Row): Reputation = {

    Reputation ( siteId(r),emailId(r),date(r),value(r))
  }

}

object ReputationRepository extends ReputationRepository with RootConnector {

  override lazy val tableName = "reputation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(reputation: Reputation): Future[ResultSet] = {
    insert
      .value(_.siteId, reputation.siteId)
      .value(_.emailId, reputation.emailId)
      .value(_.date, reputation.date)
      .value(_.value, reputation.value)
      .future()
  }


  def getDayReputation(siteId: String, emailId:String, date:DateTime): Future[Option[Reputation]] = {
    select
      .where(_.siteId eqs siteId)
      .and(_.emailId eqs emailId)
      .and(_.date eqs date)
      .one()
  }


  def getUserReputations(siteId: String, emailId:String): Future[Seq[Reputation]] = {
    select
      .where(_.siteId eqs siteId)
      .and(_.emailId eqs emailId)
      .fetchEnumerator() run Iteratee.collect()
  }
}

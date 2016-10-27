package repositories.reputations



import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.Reputation
import org.h2.engine.Session
import org.joda.time.DateTime
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future



//emailId:String
//date:DateTime
//value:Int


class ReputationRepository extends CassandraTable[ReputationRepository, Reputation]
{

  object emailId extends StringColumn(this) with PrimaryKey[String]

  object date extends DateTimeColumn(this)

  object value extends IntColumn(this)

  override def fromRow(r: Row): Reputation = {
    Reputation(
    emailId(r),
    date(r),
    value(r)
  )
}
}

object ReputationRepository extends ReputationRepository with RootConnector {

  override lazy val tableName = "reputation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(reputation: Reputation): Future[ResultSet] = {
    insert
      .value(_.emailId, reputation.emailId)
      .value(_.date, reputation.date)
      .value(_.value, reputation.value)
      .future()
  }

  def getUserByEmail(siteId: String, emailId: String): Future[Option[Reputation]] = {
    select.where(_.emailId eqs siteId).and(_.emailId eqs emailId).one()
  }

  def getSiteUsers(siteId: String): Future[Seq[Reputation]] = {
    select.where(_.emailId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }
}

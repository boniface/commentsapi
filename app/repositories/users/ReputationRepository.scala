package repositories.users

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.builder.{Chainned, Unspecified, Unordered, Unlimited}
import com.websudos.phantom.builder.query.SelectQuery
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PrimaryKey}
import com.websudos.phantom.{CassandraTable}
import com.websudos.phantom.connectors.RootConnector
import conf.connection.DataConnection._
import domain.users.Reputation
import play.api.libs.iteratee.Iteratee
import shapeless.HNil
import scala.concurrent.Future
/**
 * Created by Rosie on 2016/11/12.
 */
class ReputationRepository  extends CassandraTable[ReputationRepository, Reputation] {

  object emailId extends StringColumn(this) with PrimaryKey[String]
  object date extends DateTimeColumn(this)
  object value extends IntColumn(this)

  override def fromRow(r:Row): Reputation = {

    Reputation (emailId(r),date(r),value(r))
  }

}

object ReputationRepository extends ReputationRepository with RootConnector {

  override lazy val tableName = "reputation"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(reputation: Reputation): Future[ResultSet] = {
    insert
      .value(_.emailId, reputation.emailId)
      .value(_.date, reputation.date)
      .value(_.value, reputation.value)
      .future()
  }

  /*def getReputationByEmailId(emailId: String): SelectQuery[ReputationRepository, Reputation, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.emailId eqs emailId)
  }*/

  def getReputationByEmailId(emailId: String): Future[Option[Reputation]] = {
    select.where(_.emailId eqs emailId).one()
  }

 /*
      //Did not find the use of having another function, uncomment if necessary

  def getReputationValue(emailId: String): Future[Option[Reputation]] = {
    select.where(_.emailId eqs emailId).one()
  }*/

}
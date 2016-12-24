package repositories.comments

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.comments.Abuse

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/19.
  */

class AbuseRepository  extends CassandraTable[AbuseRepository, Abuse]{

  object siteId extends StringColumn(this) with PartitionKey[String]
  object commentIdOrResponseId extends StringColumn(this) with PrimaryKey[String]
  object emailId extends StringColumn(this) with PrimaryKey[String]
  object date extends DateTimeColumn(this) with PrimaryKey[DateTime]
  object details extends StringColumn(this)

  override def fromRow(r:Row): Abuse = {

    Abuse(siteId(r),commentIdOrResponseId(r),date(r),details(r),emailId(r))
  }
}

object AbuseRepository extends AbuseRepository with RootConnector {

  override lazy val tableName = "abuse"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(abuse: Abuse): Future[ResultSet] = {
    insert
      .value(_.siteId, abuse.siteId)
      .value(_.commentIdOrResponseId,abuse.commentIdOrResponseId)
      .value(_.details, abuse.details)
      .value(_.emailId, abuse.emailId)
      .value(_.date, abuse.date)
      .future()
  }

  def getItemAbuse(siteId: String, commentIdOrResponseId: String): Future[Seq[Abuse]] = {
    select
      .where(_.siteId eqs siteId)
      .and(_.commentIdOrResponseId eqs commentIdOrResponseId)
      .fetchEnumerator() run Iteratee.collect()
  }

}

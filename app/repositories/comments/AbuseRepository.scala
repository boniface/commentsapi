package repositories.comments

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import domain.comments.Abuse

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/19.
  */

class AbuseRepository  extends CassandraTable[AbuseRepository, Abuse]{

  object siteId extends StringColumn(this) with PartitionKey[String]
  object subjectId extends StringColumn(this) with PrimaryKey[String]
  object commentIdOrResponseId extends StringColumn(this)
  object abuseId extends StringColumn(this)
  object details extends StringColumn(this)
  object emailId extends StringColumn(this)
  object date extends DateTimeColumn(this)

  override def fromRow(r:Row): Abuse = {

    Abuse(siteId(r),subjectId(r),abuseId(r),details(r),commentIdOrResponseId(r),emailId(r),date(r))
  }
}

object AbuseRepository extends AbuseRepository with RootConnector {

  override lazy val tableName = "abuse"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(abuse: Abuse): Future[ResultSet] = {
    insert
      .value(_.subjectId, abuse.subjectId)
      .value(_.siteId, abuse.siteId)
      .value(_.abuseId, abuse.abuseId)
      .value(_.commentIdOrResponseId,abuse.commentIdOrResponseId)
      .value(_.details, abuse.details)
      .value(_.emailId, abuse.emailId)
      .value(_.date, abuse.date)
      .future()
  }

  def getAbuseBySubjectId(siteId: String, subjectId: String): Future[Option[Abuse]] = {
    select.where(_.siteId eqs siteId).and(_.subjectId eqs subjectId).one()
  }

  def getSiteAbuse(siteId: String): Future[Seq[Abuse]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }

}

package repositories.comments

import conf.connection.DataConnection
import domain.comments.Abuse
import domain.users.User
import org.h2.result.Row
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future
import scala.math.Ordering.String

/**
  * Created by hashcode on 2016/10/19.
  */
class CommentRepository  extends CassandraTable[CommentRepository, Abuse]{

  object siteId extends StringColumn(this) with PartitionKey[String]
  object subjectId extends StringColumn(this) with PrimaryKey[String]
  object commentOrResponseId extends StringColumn(this)
  object abuseId extends StringColumn(this)
  object details extends StringColumn(this)
  object emailId extends StringColumn(this)
  object date extends StringColumn(this)

  override def fromRow(r: Row): Abuse = {
    Abuse(
      siteId(r),
      subjectId(r),
      commentOrResponseId(r),
      abuseId(r),
      details(r),
      emailId(r),
      date(r)
    )
  }
}

object CommentRepository extends CommentRepository with RootConnector {

  override lazy val tableName = "abuse"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(abuse: Abuse): Future[ResultSet] = {
    insert
      .value(_.subjectId, abuse.subjectId)
      .value(_. siteId, abuse.siteId)
      .value(_. abuseId, abuse.abuseId)
      .value(_.commentOrResponseId,abuse.commentOrResponseId)
      .value(_.details, abuse.details)
      .value(_.emailId, abuse.emailId)
      .value(_.date, abuse.date)
      .future()
  }

  def getAbuseBySubjectId(siteId: String, subjectId: String): Future[Option[Abuse]] = {
    select.where(_.siteId eqs siteId).and(_.subjectId eqs subjectId).one()
  }

  def getSiteAbuse(siteId: String): Future[Seq[User]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }

}

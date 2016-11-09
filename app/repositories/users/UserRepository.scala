package repositories.users

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.comments.Abuse
import domain.users.User
import org.h2.engine.Session
import org.h2.result.Row
import views.html.helper.select

import scala.concurrent.Future

/**
  * Created siteId:String,
  * email: String,
  * screenName: String,
  * firstname:Option[String],
  * lastName:Option[String],
  * password: String
  */

class CommentRepository extends CassandraTable[CommentRepository, Abuse] {

  object siteId extends StringColumn(this) with PartitionKey[String]

  object subjectId extends StringColumn(this) with PrimaryKey[String]

  object commentOrResponse extends StringColumn(this)

  object abuseId extends OptionalStringColumn(this)

  object details extends OptionalStringColumn(this)

  object emailId extends StringColumn(this)

  object date extends StringColumn(this)


  override def fromRow(r: Row): Abuse = {
    Abuse(
      siteId(r),
      subjectId(r),
      commentOrResponse(r),
      abuseId(r),
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
      .value(_.commentOrResponse, abuse.commentOrResponse)
      .value(_.abuseId, abuse.abuseId)
      .value(_.emailId, abuse.emailId)
      .value(_.siteId, abuse.siteId)
      .value(_.date, abuse.date)
      .future()
  }

  def getUserBySubject(siteId: String, subjectId: String): Future[Option[User]] = {
    select.where(_.siteId eqs siteId).and(_.subjectId eqs subjectId).one()
  }

  def getSiteAbuse(siteId: String): Future[Seq[User]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }
}

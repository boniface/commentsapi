package repositories.comments

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.comments.Comment

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/24.
  */
class SingleCommentRepository extends CassandraTable[SingleCommentRepository, Comment] {

  object commentId extends StringColumn(this) with PartitionKey[String]

  object siteId extends StringColumn(this) with PrimaryKey[String]

  object subjectId extends StringColumn(this) with PrimaryKey[String]

  object emailId extends StringColumn(this)

  object ipaddress extends StringColumn(this)

  object comment extends StringColumn(this)

  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): Comment = {
    Comment(
      siteId(r),
      subjectId(r),
      commentId(r),
      emailId(r),
      ipaddress(r),
      comment(r),
      date(r)
    )
  }
}

object SingleCommentRepository extends SingleCommentRepository with RootConnector {

  override lazy val tableName = "singlecomments"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(comment: Comment): Future[ResultSet] = {
    insert
      .value(_.subjectId, comment.subjectId)
      .value(_.siteId, comment.siteId)
      .value(_.comment, comment.comment)
      .value(_.emailId, comment.emailId)
      .value(_.ipaddress, comment.ipaddress)
      .value(_.comment, comment.comment)
      .value(_.date, comment.date)
      .future()
  }

  def getComment(commentId: String): Future[Option[Comment]] = {
    select
      .where(_.commentId eqs commentId)
      .one()
  }
}

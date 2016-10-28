package repositories.comments

import conf.connection.DataConnection
import domain.comments.{Abuse, Comment}
import domain.users.User
import io.netty.util.concurrent.Future
import org.h2.engine.Session
import org.h2.result.Row
import views.html.helper.select

/**
  * Created by Bonga on 10/28/2016.
  */

class CommentRepository  extends CassandraTable[CommentRepository, Comment]{


  object siteId extends StringColumn(this) with PartitionKey[String]
  object subjectId extends StringColumn(this) with PrimaryKey[String]
  object commentId extends StringColumn(this)
  object emailId extends StringColumn(this)
  object ipaddress extends StringColumn(this)
  object comment extends StringColumn(this)
  object date extends StringColumn(this)

object date extends StringColumn(this)

override def fromRow(r: Row): Comment = {
  Comment(
    siteId(r),
    subjectId(r),
    commentId(r),
    emailId(r),
    ipaddress(r),
    comment(r),
    date(r),

)
}
}

object CommentRepository extends CommentRepository with RootConnector {

  override lazy val tableName = "abuse"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(comment: Comment): Future[ResultSet] = {
    insert
      .value(_.subjectId, comment.subjectId)
      .value(_. siteId, comment.siteId)
      .value(_. comment, comment.comment)
      .value(_.emailId, comment.emailId)
      .value(_.ipaddress,comment.ipaddress)
      .value(_. comment, comment.comment)
      .value(_.date, comment.date)
      .future()
  }

  def getCommentBySubjectId(siteId: String, subjectId: String): Future[Option[Abuse]] = {
    select.where(_.siteId eqs siteId).and(_.subjectId eqs subjectId).one()
  }

  def getSiteComment(siteId: String): Future[Seq[User]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }

}

package repositories.comments

import conf.connection.DataConnection
import domain.comments.{CommentStatus, Abuse, Comment}
import domain.users.User
import io.netty.util.concurrent.Future
import org.h2.engine.Session
import org.h2.result.Row
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

/**
  * Created by Bonga on 10/28/2016.
  */

class CommentStatusRepository  extends CassandraTable[CommentStatusRepository, CommentStatus]{


  object commentId extends StringColumn(this) with PartitionKey[String]
  object status extends StringColumn(this)
  object date extends StringColumn(this)

  override def fromRow(r: Row): CommentStatus = {
    CommentStatus(
      commentId(r),
      status(r),
      date(r),

    )
  }
}

object CommentRepository extends CommentRepository with RootConnector {

  override lazy val tableName = "CommentStatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(commentStatus: CommentStatus): Future[ResultSet] = {
    insert
      .value(_.commentId, commentStatus.commentId)
      .value(_. status, commentStatus.status)
      .value(_.date, commentStatus.date)
      .future()
  }

  def getCommentBycommentId(commentId: String): Future[Option[CommentStatus]] = {
    select.where(_.siteId eqs siteId).and(_.commentId eqs commentId).one()
  }

  def getSiteCommentStatus(commentId: String): Future[Seq[User]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }

}

package repositories.comments


import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.comments.CommentStatus
import scala.concurrent.Future


/**
  * Created by Bonga on 10/28/2016.
  */

class CommentStatusRepository  extends CassandraTable[CommentStatusRepository, CommentStatus] {


  object commentId extends StringColumn(this) with PrimaryKey[String]

  object status extends StringColumn(this)

  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): CommentStatus = {
    CommentStatus(
      commentId(r),
      status(r),
      date(r)
    )
  }
}

object CommentStatusRepository extends CommentStatusRepository with RootConnector {

  override lazy val tableName = "CommentStatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(commentStatus: CommentStatus): Future [ResultSet] = {
    insert
      .value(_.commentId, commentStatus.commentId)
        .value(_.status,commentStatus.status)
      .value(_.date, commentStatus.date)
      .future()
  }

  def getCommentBycommentId(commentsId: String): Future[Option[CommentStatus]] = {
    select.where(_.commentId eqs commentsId).one()
  }

  def getSiteCommentStatus(commentId: String): Future[Seq[CommentStatus]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }


}

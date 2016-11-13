package repositories.comments
import com.datastax.driver.core.ResultSet
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.{KeySpace, RootConnector}
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PrimaryKey
import domain.comments.{Comment, Abuse, CommentStatus}
import conf.connection.DataConnection
import repositories.comments.AbuseRepository._
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

  def getCommentStatusBycommentId(commentId: String): Future[Option[CommentStatus]] = {
    select.where(_.commentId eqs commentId).one()
  }


  def getSiteCommentStatus(commentId: String): Future[Option[CommentStatus]] = {
    select.where(_.commentId eqs commentId).one()
  }
  def getAllCommentStatus: Future[Seq[CommentStatus]] = {
    select.all().fetch()
  }


  def deleteAll: Future[ResultSet] = {
    delete.future()

  }



}
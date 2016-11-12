package repositories.comments
import com.datastax.driver.core.{Row, ResultSet}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.{KeySpace, RootConnector}
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams.iteratee.Iteratee
import conf.connection.DataConnection
import domain.comments.Comment

import scala.concurrent.Future


/**
  * Created by Bonga on 10/28/2016.
  */

class CommentRepository  extends CassandraTable[CommentRepository, Comment] {


  object siteId extends StringColumn(this) with PartitionKey[String]

  object subjectId extends StringColumn(this) with PrimaryKey[String]

  object commentId extends StringColumn(this)

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

  object CommentRepository extends CommentRepository with RootConnector {

    override lazy val tableName = "abuse"

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


    def getCommentBySubjectId(siteId: String, subjectId: String): Future[Option[Comment]] = {
      select.where(_.siteId eqs siteId).and(_.subjectId eqs subjectId).one()
    }

    def getSiteComment(siteId: String): Future[Seq[Comment]] = {
      select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
    }


}
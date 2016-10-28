package repositories.comments

import conf.connection.DataConnection
import domain.comments.{Response, Abuse, Comment}
import domain.users.User
import io.netty.util.concurrent.Future
import org.h2.engine.Session
import org.h2.result.Row
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

/**
  * Created by Bonga on 10/28/2016.
  */

class ResponseRepository  extends CassandraTable[ResponseRepository, Response]{


  object commentId extends StringColumn(this) with PartitionKey[String]
  object responseId extends StringColumn(this) with PrimaryKey[String]
  object response extends StringColumn(this)
  object emailId extends StringColumn(this)
  object ipaddress extends StringColumn(this)
  object date extends StringColumn(this)

  override def fromRow(r: Row): Response = {
    Response(
      commentId(r),
      responseId(r),
      response(r),
      emailId(r),
      ipaddress(r),
      date(r),

    )
  }
}

object ResponseRepository extends ResponseRepository with RootConnector {

  override lazy val tableName = "response"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(response: Response): Future[ResultSet] = {
    insert
      .value(_.commentId, response.commentId)
      .value(_. responseId, response.responseId)
      .value(_. response, response.response)
      .value(_.emailId, response.emailId)
      .value(_.ipaddress,response.ipaddress)
      .value(_.date, response.date)
      .future()
  }

  def getResponseByCommentId(commentId: String, responseId: String): Future[Option[Response]] = {
    select.where(_.siteId eqs siteId).and(_.subjectId eqs subjectId).one()
  }

  def getSiteResponse(commentId: String): Future[Seq[User]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }

}

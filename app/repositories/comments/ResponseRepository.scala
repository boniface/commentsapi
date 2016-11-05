package repositories.comments


import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.{KeySpace, RootConnector}
import com.websudos.phantom.dsl._
import domain.comments.Response
import play.api.libs.iteratee.Iteratee
import conf.connection.DataConnection
import scala.concurrent.Future



/**
  * Created by Bonga on 10/28/2016.
  */

class ResponseRepository  extends CassandraTable[ResponseRepository,Response]{


  object commentId extends StringColumn(this) with PartitionKey[String]
  object responseId extends StringColumn(this) with PrimaryKey[String]
  object response extends StringColumn(this)
  object emailId extends StringColumn(this)
  object ipaddress extends StringColumn(this)
  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): Response = {
    Response(
      commentId(r),
      responseId(r),
      response(r),
      emailId(r),
      ipaddress(r),
      date(r)

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
      .value(_.responseId, response.responseId)
      .value(_.response, response.response)
      .value(_.emailId, response.emailId)
      .value(_.ipaddress,response.ipaddress)
      .value(_.date, response.date)
      .future()
  }

  def getResponseByCommentId(commentId: String, responseId: String): Future[Option[Response]] = {
    select.where(_.commentId eqs commentId).and(_.responseId eqs responseId).one()
  }

  def getSiteResponse(commentId: String): Future[Seq[Response]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }

}

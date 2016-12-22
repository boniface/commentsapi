package repositories.comments

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.comments.Response

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/22.
  */
class ResponseByUserRepository extends CassandraTable[ResponseRepository,Response]{
  object emailId extends StringColumn(this) with PartitionKey[String]
  object responseId extends StringColumn(this) with PrimaryKey[String]
  object commentId extends StringColumn(this)
  object response extends StringColumn(this)
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

  override lazy val tableName = "userresponse"

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

  def getUserResponses(emailId: String): Future[Seq[Response]] = {
    select
      .where(_.emailId eqs emailId)
      .fetchEnumerator() run Iteratee.collect()
  }
}

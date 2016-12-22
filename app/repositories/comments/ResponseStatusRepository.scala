package repositories.comments


import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PartitionKey, PrimaryKey}
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.comments.ResponseStatus
import org.joda.time.DateTime

import scala.concurrent.Future

/**
  * Created by Bonga on 10/28/2016.
  */

class ResponseStatusRepository extends CassandraTable[ResponseStatusRepository, ResponseStatus] {

  object responseId extends StringColumn(this) with PartitionKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Ascending

  object status extends StringColumn(this)


  override def fromRow(r: Row): ResponseStatus = {
    ResponseStatus(
      responseId(r),
      status(r),
      date(r)
    )
  }
}

object ResponseStatusRepository extends ResponseStatusRepository with RootConnector {

  override lazy val tableName = "responseStatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(responseStatus: ResponseStatus): Future[ResultSet] = {
    insert
      .value(_.responseId, responseStatus.responseId)
      .value(_.status, responseStatus.status)
      .value(_.date, responseStatus.date)
      .future()
  }

  def getResponseStatus(responseId: String): Future[Seq[ResponseStatus]] = {
    select
      .where(_.responseId eqs responseId)
      .fetchEnumerator() run Iteratee.collect()
  }

}


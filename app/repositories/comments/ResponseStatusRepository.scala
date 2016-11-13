package repositories.comments


import com.datastax.driver.core.ResultSet
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.{KeySpace, RootConnector}
import com.websudos.phantom.dsl._
import conf.connection.DataConnection
import domain.comments.{Abuse, ResponseStatus}
import repositories.comments.AbuseRepository._
import scala.concurrent.Future

/**
  * Created by Bonga on 10/28/2016.
  */

class ResponseStatusRepository  extends CassandraTable[ResponseStatusRepository, ResponseStatus]{


  object responseId extends StringColumn(this) with PrimaryKey[String]
  object status extends StringColumn(this)
  object date extends DateTimeColumn(this)

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
      .value(_. status, responseStatus.status)
      .value(_.date, responseStatus.date)
      .future()
  }

  def getResponseStatusByResponseId(responsetId: String): Future[Option[ResponseStatus]] = {
    select.where(_.responseId eqs responsetId).one()
  }

  def getAllResponseStatus: Future[Seq[ResponseStatus]] = {
    select.all().fetch()
  }


  def deleteAll: Future[ResultSet] = {
    delete.future()

  }


}


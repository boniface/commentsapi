package repositories.comments

import conf.connection.DataConnection
import domain.comments.{ResponseStatus, CommentStatus}
import domain.users.User
import io.netty.util.concurrent.Future
import org.h2.engine.Session
import org.h2.result.Row
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

/**
  * Created by Bonga on 10/28/2016.
  */

class ResponseStatusRepository  extends CassandraTable[ResponseStatusRepository, ResponseStatus]{


  object responsetId extends StringColumn(this) with PartitionKey[String]
  object status extends StringColumn(this)
  object date extends StringColumn(this)

  override def fromRow(r: Row): ResponseStatus = {
    ResponseStatus(
      responsetId(r),
      status(r),
      date(r),

    )
  }
}

object ResponseStatusRepository extends ResponseStatusRepository with RootConnector {

  override lazy val tableName = "responseStatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(responseStatus: ResponseStatus): Future[ResultSet] = {
    insert
      .value(_.responsetId, responseStatus.responsetId)
      .value(_. status, responseStatus.status)
      .value(_.date, responseStatus.date)
      .future()
  }

  def getResponsetByResponseId(responsetId: String): Future[Option[CommentStatus]] = {
    select.where(_.siteId eqs siteId).and(_.responsetId eqs responsetId).one()
  }

  def getSiteResponsetId(responsetId: String): Future[Seq[User]] = {
    select.where(_.siteId eqs responsetId).fetchEnumerator() run Iteratee.collect()
  }

}


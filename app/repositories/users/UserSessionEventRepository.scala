package repositories.users

import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserSessionEvent
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/**
sessionId: String,
id: String,
eventTime: DateTime,
eventName: String
  */


class UserSessionEventRepository extends CassandraTable[UserSessionEventRepository, UserSessionEvent] {

  object sessionId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object eventTime extends DateTimeColumn(this)

  object eventName extends StringColumn(this)

  override def fromRow(r: Row): UserSessionEvent = {
    UserSessionEvent(
      sessionId(r),
      id(r),
      eventTime(r),
      eventName(r)
  )
}
}

object UserSessionEventRepository extends UserSessionEventRepository with RootConnector {

  override lazy val tableName = "userSessionEvent"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userSessionEvent: UserSessionEvent): Future[ResultSet] = {
    insert
      .value(_.sessionId, userSessionEvent.sessionId)
      .value(_.id, userSessionEvent.id)
      .value(_.eventTime, userSessionEvent.eventTime)
      .value(_.eventName, userSessionEvent.eventName)
      .future()
  }

  def getUserByEmail(sessionId: String, id: String): Future[Option[UserSessionEvent]] = {
    select.where(_.sessionId eqs sessionId).and(_.id eqs id).one()
  }

  def getSiteUsers(id: String): Future[Seq[UserSessionEvent]] = {
    select.where(_.id eqs id).fetchEnumerator() run Iteratee.collect()
  }

}

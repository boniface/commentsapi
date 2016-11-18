package repositories.users

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PrimaryKey, PartitionKey}
import com.websudos.phantom.{CassandraTable}
import com.websudos.phantom.connectors.RootConnector
import conf.connection.DataConnection._
import domain.users.UserSessionEvent
import play.api.libs.iteratee.Iteratee
import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/14.
 */
class UserSessionEventRepository extends CassandraTable[UserSessionEventRepository, UserSessionEvent]{

  object sessionId extends StringColumn(this) with PartitionKey[String]
  object id extends StringColumn(this) with PrimaryKey[String]
  object eventName extends StringColumn(this)
  object eventTime extends DateTimeColumn(this)

  override def fromRow(r:Row): UserSessionEvent = {

    UserSessionEvent(sessionId(r),id(r),eventTime(r),eventName(r))
  }

}

object UserSessionEventRepository extends UserSessionEventRepository with RootConnector {

  override lazy val tableName = "userSessionEvent"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(sessionevent: UserSessionEvent): Future[ResultSet] = {
    insert
      .value(_.id, sessionevent.id)
      .value(_.sessionId, sessionevent.sessionId)
      .value(_.eventTime, sessionevent.eventTime)
      .value(_.eventName,sessionevent.eventName)
      .future()
  }

  def getEventNameById(id: String, sessionId: String): Future[Option[UserSessionEvent]] = {
    select.where(_.id eqs id).and(_.sessionId eqs sessionId).one()
  }

  def getSessionEvent(id: String): Future[Seq[UserSessionEvent]] = {
    //select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
    select.where(_.id eqs id).fetch()

  }

}

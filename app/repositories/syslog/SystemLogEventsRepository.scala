package repositories.syslog

import com.datastax.driver.core.{Row, Session}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.{KeySpace, RootConnector}
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.syslog.SystemLogEvents

import scala.concurrent.Future


/**
  * Created by Quest on 2016/10/25.
  */
class SystemLogEventsRepository extends CassandraTable[SystemLogEventsRepository, SystemLogEvents] {

  object siteId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object eventName extends StringColumn(this)

  object eventType extends StringColumn(this)

  object message extends StringColumn(this)

  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): SystemLogEvents = {
    SystemLogEvents(siteId(r), id(r), eventName(r), eventType(r), message(r), date(r))
  }
}

object SystemLogEventsRepository extends SystemLogEventsRepository with RootConnector {
  override lazy val tableName = "systemLogEvents"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(systemLogEvents: SystemLogEvents): Future[ResultSet] = {
    insert
      .value(_.siteId, systemLogEvents.siteId)
      .value(_.id, systemLogEvents.id)
      .value(_.eventName, systemLogEvents.eventName)
      .value(_.eventType, systemLogEvents.eventType)
      .value(_.message, systemLogEvents.message)
      .value(_.date, systemLogEvents.date)
      .ttl(5000)
      .future()

  }

  def getEventById(siteId: String, id: String): Future[Option[SystemLogEvents]] = {
    select
      .where(_.siteId eqs siteId)
      .and(_.id eqs id)
      .one()
  }

  def getSiteLogs(siteId: String): Future[Seq[SystemLogEvents]] = {
    select
      .where(_.siteId eqs siteId)
      .fetchEnumerator() run Iteratee.collect()
  }


}
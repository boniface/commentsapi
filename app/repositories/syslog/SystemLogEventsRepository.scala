package repositories.syslog

import com.datastax.driver.core.{Session, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.{KeySpace, RootConnector}
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.syslog.SystemLogEvents


import scala.concurrent.Future


/**
  * Created by Quest on 2016/10/25.
  */
class SystemLogEventsRepository extends CassandraTable[SystemLogEventsRepository,SystemLogEvents]{

  object orgCode extends StringColumn(this)
  object id extends StringColumn(this) with PrimaryKey[String]
  object eventName extends StringColumn(this)
  object eventType extends StringColumn(this)
  object message extends StringColumn(this)
  object date extends DateTimeColumn(this) with PartitionKey[DateTime]

  override def fromRow(r:Row):SystemLogEvents ={
    SystemLogEvents(orgCode(r),id(r),eventName(r),eventType(r),message(r),date(r))
  }
}

object SystemLogEventsRepository extends SystemLogEventsRepository with RootConnector{
  override lazy val tableName = "systemLogEvents"
  override implicit def space: KeySpace = DataConnection.keySpace
  override implicit def session: Session = DataConnection.session

  def save(systemLogEvents: SystemLogEvents) : Future[ResultSet] ={
    insert
      .value(_.orgCode,systemLogEvents.orgCode)
      .value(_.id,systemLogEvents.id)
      .value(_.eventName,systemLogEvents.eventName)
      .value(_.eventType,systemLogEvents.eventType)
      .value(_.message,systemLogEvents.message)
      .value(_.date,systemLogEvents.date)
      .future()
  }

  def getEventById(id:String): Future[Option[SystemLogEvents]] = {
    select.where(_.id eqs id).one()
  }

  def getEventsByDate(date:DateTime):Future[Option[SystemLogEvents]] ={
    select.where(_.date eqs date).one()
  }

  def getEvents:Future[Seq[SystemLogEvents]]= {
    select.all().fetch()
  }

  def deleteById(id: String):Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
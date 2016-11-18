package repositories.users

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PrimaryKey, PartitionKey}
import com.websudos.phantom.{CassandraTable}
import com.websudos.phantom.connectors.RootConnector
import conf.connection.DataConnection._
import domain.users.UserSession
import play.api.libs.iteratee.Iteratee
import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/14.
 */
class UserSessionRepository extends CassandraTable[UserSessionRepository,UserSession]{

  object tokenId extends StringColumn(this) with PartitionKey[String]
  object userSessionId extends StringColumn(this) with PrimaryKey[String]
  object ipaddress extends StringColumn(this)
  object browserSession extends StringColumn(this)
  object status extends StringColumn(this)
  object email extends StringColumn(this)
  object startTime extends DateTimeColumn(this)

  override def fromRow(r:Row):UserSession = {

    UserSession(email(r),userSessionId(r),startTime(r),ipaddress(r),browserSession(r),status(r),tokenId(r))
  }

}

object UserSessionRepository extends UserSessionRepository with RootConnector {

  override lazy val tableName = "userSession"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(sessions:UserSession): Future[ResultSet] = {
    insert
      .value(_.tokenId, sessions.tokenId)
      .value(_.userSessionId, sessions.userSessionId)
      .value(_.email, sessions.email)
      .value(_.browserSession, sessions.browserSession)
      .value(_.startTime, sessions.startTime)
      .value(_.ipaddress, sessions.ipaddress)
      .value(_.status, sessions.status)
      .future()
  }

  def getEmailByUserSessionId( userSessionId: String): Future[Option[UserSession]] = {
    select.where(_.userSessionId eqs userSessionId).one()
  }

  def getUserSession(tokenId: String): Future[Seq[UserSession]] = {
    //select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
    select.where(_.tokenId eqs tokenId).fetch()

  }

}

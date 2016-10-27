package repositories.users

import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserSession
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/**
email:String,
userSessionId:String,
startTime:DateTime,
ipaddress:String,
browserSession:String,
status:String,
tokenId:String
  */
class UserSessionRepository extends CassandraTable[UserSessionRepository, UserSession] {

  object email extends StringColumn(this) with PartitionKey[String]

  object userSessionId extends StringColumn(this) with PrimaryKey[String]

  object startTime extends DateTimeColumn(this)

  object ipaddress extends StringColumn(this)

  object browserSession extends StringColumn(this)

  object status extends StringColumn(this)

  object tokenId extends StringColumn(this)

  override def fromRow(r: Row): UserSession = {
    UserSession(
      email(r),
      userSessionId(r),
      startTime(r),
      ipaddress(r),
      browserSession(r),
      status(r),
      tokenId(r)
    )
  }
}

object UserSessionRepository extends UserSessionRepository with RootConnector {

  override lazy val tableName = "userSession"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userSession: UserSession): Future[ResultSet] = {
    insert
      .value(_.email, userSession.email)
      .value(_.userSessionId, userSession.userSessionId)
      .value(_.startTime, userSession.startTime)
      .value(_.ipaddress, userSession.ipaddress)
      .value(_.browserSession, userSession.browserSession)
      .value(_.status, userSession.status)
      .value(_.tokenId, userSession.tokenId)
      .future()
  }

  def getUserByEmail(userSessionId: String, email: String): Future[Option[UserSession]] = {
    select.where(_.userSessionId eqs userSessionId).and(_.email eqs email).one()
  }

  def getSiteUsers(userSessionId: String): Future[Seq[UserSession]] = {
    select.where(_.userSessionId eqs userSessionId).fetchEnumerator() run Iteratee.collect()
  }
}

package repositories.users

import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserStatus
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/**
siteId:String,
userId:String,
status:String,
date: DateTime)
  */
class UserStatusRepository extends CassandraTable[UserStatusRepository, UserStatus] {

  object siteId extends StringColumn(this) with PartitionKey[String]

  object userId extends StringColumn(this) with PrimaryKey[String]

  object status extends StringColumn(this)

  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): UserStatus = {
    UserStatus(
      siteId(r),
      userId(r),
      status(r),
      date(r)
    )
  }
}

object UserStatusRepository extends UserStatusRepository with RootConnector {

  override lazy val tableName = "userStatusRepository"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userStatus: UserStatus): Future[ResultSet] = {
    insert
      .value(_.siteId, userStatus.siteId)
      .value(_.userId, userStatus.userId)
      .value(_.status, userStatus.status)
      .value(_.date, userStatus.date)
      .future()
  }

  def getUserByEmail(siteId: String, userId: String): Future[Option[UserStatus]] = {
    select.where(_.siteId eqs siteId).and(_.userId eqs userId).one()
  }

  def getSiteUsers(userId: String): Future[Seq[UserStatus]] = {
    select.where(_.userId eqs userId).fetchEnumerator() run Iteratee.collect()
  }
}
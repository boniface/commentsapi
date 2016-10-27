package repositories.users

import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserRole
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/**
emailId: String,
roleId: String,
  date:DateTime
  */
class UserRoleRepository extends CassandraTable[UserRoleRepository, UserRole] {

  object emailId extends StringColumn(this) with PartitionKey[String]

  object roleId extends StringColumn(this) with PrimaryKey[String]

  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): UserRole = {
    UserRole(
      emailId(r),
      roleId(r),
      date(r)
    )
  }
}


object UserRoleRepository extends UserRoleRepository with RootConnector {

  override lazy val tableName = "userRole"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userRole: UserRole): Future[ResultSet] = {
    insert
      .value(_.emailId, userRole.emailId)
      .value(_.roleId, userRole.roleId)
      .value(_.date, userRole.date)
      .future()
  }

  def getUserByEmail(roleId: String, emailId: String): Future[Option[UserRole]] = {
    select.where(_.roleId eqs roleId).and(_.emailId eqs emailId).one()
  }

  def getRoleId(roleId: String): Future[Seq[UserRole]] = {
    select.where(_.roleId eqs roleId).fetchEnumerator() run Iteratee.collect()
  }
}






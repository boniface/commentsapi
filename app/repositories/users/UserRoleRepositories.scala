package repositories.users

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PrimaryKey, PartitionKey}
import com.websudos.phantom.{CassandraTable}
import com.websudos.phantom.connectors.RootConnector
import conf.connection.DataConnection._
import domain.users.UserRole
import play.api.libs.iteratee.Iteratee
import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/14.
 */
class UserRoleRepository extends CassandraTable[UserRoleRepository,UserRole]{

  object emailId extends StringColumn(this) with PrimaryKey[String]
  object roleId extends StringColumn(this)
  object date extends DateTimeColumn(this)

  override def fromRow(r:Row): UserRole = {

   UserRole(emailId(r),roleId(r),date(r))
  }
}

object UserRoleRepository extends UserRoleRepository with RootConnector {

  override lazy val tableName = "userRole"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(role: UserRole): Future[ResultSet] = {
    insert
      .value(_.emailId, role.emailId)
      .value(_.roleId, role.roleId)
      .value(_.date,role.date)
      .future()
  }

  def getRoleByEmailId(emailId: String): Future[Option[UserRole]] = {
    select.where(_.emailId eqs emailId).one()
  }

  def getRoleId(emailId:String,roleId:String):Future[Seq[UserRole]] = {
  select.where (_.emailId eqs emailId).fetch()
}


}


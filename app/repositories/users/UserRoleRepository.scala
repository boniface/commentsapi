package repositories.users

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PrimaryKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection._
import domain.users.UserRole
import org.joda.time.DateTime

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/14.
 */
class UserRoleRepository extends CassandraTable[UserRoleRepository,UserRole]{
  object siteId extends StringColumn(this) with PartitionKey[String]
  object emailId extends StringColumn(this) with PartitionKey[String]
  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Ascending
  object roleId extends StringColumn(this)


  override def fromRow(r:Row): UserRole = {

    UserRole(siteId(r), emailId(r),roleId(r),date(r))
  }
}

object UserRoleRepository extends UserRoleRepository with RootConnector {

  override lazy val tableName = "userroles"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(role: UserRole): Future[ResultSet] = {
    insert
      .value(_.siteId, role.siteId)
      .value(_.emailId, role.emailId)
      .value(_.roleId, role.roleId)
      .value(_.date,role.date)
      .future()
  }

  def getRoleByEmailId(siteId:String, emailId: String): Future[Seq[UserRole]] = {
    select
      .where(_.siteId eqs siteId)
      .and(_.emailId eqs emailId)
      .fetchEnumerator() run Iteratee.collect()
  }
}

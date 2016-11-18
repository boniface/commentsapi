package repositories.users


import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PrimaryKey, PartitionKey}
import com.websudos.phantom.{CassandraTable}
import com.websudos.phantom.connectors.RootConnector
import conf.connection.DataConnection._
import domain.users.UserStatus
import play.api.libs.iteratee.Iteratee
import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/14.
 */
class UserStatusRepository extends CassandraTable[UserStatusRepository,UserStatus]{


  object siteId extends StringColumn(this) with PartitionKey[String]
  object userId extends StringColumn(this) with PrimaryKey[String]
  object status extends StringColumn(this)
  object date extends DateTimeColumn(this)

  override def fromRow(r:Row): UserStatus = {

    UserStatus(siteId(r),userId(r),status(r),date(r))
  }

}

object UserStatusRepository extends UserStatusRepository with RootConnector {

  override lazy val tableName = "userStatus"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(userstatus: UserStatus): Future[ResultSet] = {
    insert
      .value(_.siteId, userstatus.siteId)
      .value(_.userId, userstatus.userId)
      .value(_.status, userstatus.status)
      .value(_.date, userstatus.date)
      .future()
  }

  def getStatusByUserId(userId: String): Future[Option[UserStatus]] = {
    select.where(_.userId eqs userId).one()
  }

  def getUserStatusBySite(siteId: String,userId: String): Future[Seq[UserStatus]] = {
    //select.where(_.siteId eqs siteId)fetchEnumerator() run Iteratee.collect()
    select.where(_.siteId eqs siteId).and (_.userId eqs userId).fetch()

  }

}

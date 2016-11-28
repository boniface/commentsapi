package repositories.users

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PartitionKey, PrimaryKey}
import conf.connection.DataConnection._
import domain.users.UserLogActivities

import scala.concurrent.Future
/**
 * Created by Rosie on 2016/11/14.
 */
class UserLogActivitiesRepository  extends CassandraTable[UserLogActivitiesRepository,UserLogActivities] {


object id extends StringColumn(this) with PrimaryKey[String]
object sessionId extends StringColumn(this) with PartitionKey[String]
object emailId extends StringColumn(this)
object details extends StringColumn(this)
object date extends DateTimeColumn(this)
object description extends StringColumn(this)


override def fromRow(r:Row): UserLogActivities = {

UserLogActivities(id(r),sessionId(r),emailId(r),details(r),date(r),description(r))
}

}

object UserLogActivitiesRepository extends UserLogActivitiesRepository with RootConnector {

  override lazy val tableName = "userLogActivities"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(log: UserLogActivities): Future[ResultSet] = {
    insert
      .value(_.id, log.id)
      .value(_.sessionId, log.sessionId)
      .value(_.emailId, log.emailId)
      .value(_.details,log.details)
      .value(_.date, log.date)
      .value(_.description,log.description)
      .future()
  }

  def getAllLogActivities: Future[Seq[UserLogActivities]] = {
    select.all().fetch()
  }

  def deleteById(id: String):Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
  def getEmailIdById(id: String): Future[Option[UserLogActivities]] = {
    select.where(_.id eqs id).one()
  }


  def getLogDetails(id: String):Future[Option[UserLogActivities]]={

    select.where(_.id eqs id).one()
  }

}

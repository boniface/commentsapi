package repositories.users

import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserLogActivities
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/*(emailId: String,
id: String,
sessionId: String,
details: String,
date: DateTime,
description: String)*/

/**
  * Created by fatimam on 25/10/2016.
  */
class UserLogActivitiesRepository extends CassandraTable[UserLogActivitiesRepository, UserLogActivities] {

  object emailId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object sessionId extends StringColumn(this)

  object details extends StringColumn(this)

  object date extends DateTimeColumn(this)

  object description extends StringColumn(this)

  override def fromRow(r: Row): UserLogActivities = {
    UserLogActivities(
      emailId(r),
      id(r),
      sessionId(r),
      details(r),
      date(r),
      description(r)
  )
}
}

object UserLogActivitiesRepository extends UserLogActivitiesRepository with RootConnector {

  override lazy val tableName = "userLogActivities"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userLogActivities: UserLogActivities): Future[ResultSet] = {
    insert
      .value(_.emailId, userLogActivities.emailId)
      .value(_.id, userLogActivities.id)
      .value(_.sessionId, userLogActivities.sessionId)
      .value(_.details, userLogActivities.details)
      .value(_.date, userLogActivities.date)
      .value(_.description, userLogActivities.description)
      .future()

  }

  def getUserByEmail(emailId: String): Future[Option[UserLogActivities]] = {
    select.where(_.emailId eqs emailId).one()
  }

}
package repositories.users

import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.UserGeneratedToken
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/**
  * Created by fatimam on 25/10/2016.
  */

//token: String, status: String,  message: String, siteId: String

class UserGeneratedTokenRepository  extends CassandraTable[UserGeneratedTokenRepository, UserGeneratedToken]
{
  object siteId extends StringColumn(this) with PartitionKey[String]

  object token extends StringColumn(this)

  object status extends StringColumn(this)

  object message extends StringColumn(this)

  override def fromRow(r: Row): UserGeneratedToken = {
    UserGeneratedToken(
      siteId(r),
      token(r),
      status(r),
      message(r)

    )
  }

}

object UserGeneratedTokenRepository extends UserGeneratedTokenRepository with RootConnector {

  override lazy val tableName = "userGeneratedToken"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userGeneratedToken: UserGeneratedToken): Future[ResultSet] = {
    insert
      .value(_.token, userGeneratedToken.token)
      .value(_.status, userGeneratedToken.status)
      .value(_.message, userGeneratedToken.message)
      .value(_.siteId, userGeneratedToken.siteId)
      .future()
  }

  def getUserByEmail(siteId: String, email: String): Future[Option[UserGeneratedTokenRepository]] = {
    select.where(_.siteId eqs siteId).one()
  }

  def getSiteUsers(siteId: String): Future[Seq[UserGeneratedTokenRepository]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }
}
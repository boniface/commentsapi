package repositories.users

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PrimaryKey, PartitionKey}
import com.websudos.phantom.{CassandraTable}
import com.websudos.phantom.connectors.RootConnector
import conf.connection.DataConnection._
import domain.users.UserGeneratedToken
import play.api.libs.iteratee.Iteratee
import scala.concurrent.Future
/**
 * Created by Rosie on 2016/11/14.
 */

class UserTokenRepository extends CassandraTable[UserTokenRepository,UserGeneratedToken]{

  object siteId extends StringColumn(this) with PartitionKey[String]
  object token extends StringColumn(this) with PrimaryKey[String]
  object status extends StringColumn(this)
  object message extends StringColumn(this)


  override def fromRow(r:Row): UserGeneratedToken = {

    UserGeneratedToken(token(r),siteId(r),status(r),message(r))
  }
}

object UserTokenRepository extends UserTokenRepository with RootConnector {

  override lazy val tableName = "userGeneratedToken"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(tokens: UserGeneratedToken): Future[ResultSet] = {
    insert
      .value(_.token, tokens.token)
      .value(_.siteId, tokens.siteId)
      .value(_.status, tokens.status)
      .value(_.message, tokens.message)
      .future()
  }

  def getStatusBySiteId(siteId: String, token: String): Future[Option[UserGeneratedToken]] = {
    select.where(_.siteId eqs siteId).and(_.token eqs token).one()
  }


}
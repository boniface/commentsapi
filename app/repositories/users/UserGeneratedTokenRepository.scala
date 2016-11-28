package repositories.users

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PartitionKey, PrimaryKey}
import conf.connection.DataConnection._
import domain.users.UserGeneratedToken

import scala.concurrent.Future

/**
 * Created by Rosie on 2016/11/14.
 */
class UserGeneratedTokenRepository extends CassandraTable[UserGeneratedTokenRepository,UserGeneratedToken]{

  object siteId extends StringColumn(this) with PartitionKey[String]
  object token extends StringColumn(this) with PrimaryKey[String]
  object status extends StringColumn(this)
  object message extends StringColumn(this)


  override def fromRow(r:Row): UserGeneratedToken = {

    UserGeneratedToken(token(r),siteId(r),status(r),message(r))
  }
}

object UserGeneratedTokenRepository extends UserGeneratedTokenRepository with RootConnector {

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

  def getStatusBySiteId(token: String): Future[Option[UserGeneratedToken]] = {
    select.where(_.token eqs token).one()
  }

  def getAllTokens: Future[Seq[UserGeneratedToken]] = {
    select.all().fetch()
  }

  def deleteById(token: String):Future[ResultSet] = {
    delete.where(_.token eqs token).future()
  }
}

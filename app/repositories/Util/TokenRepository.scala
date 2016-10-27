package repositories.Util

import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.Token
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/**
  * Created by fatimam on 27/10/2016.
  */
class TokenRepository extends CassandraTable[TokenRepository, Token] {

object id extends StringColumn(this) with PrimaryKey[String]

object tokenValue extends StringColumn(this)


override def fromRow(r: Row): Token = {
  Token(
id(r),
    tokenValue(r)
)
}
}

object TokenRepository extends TokenRepository with RootConnector {

  override lazy val tableName = "token"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(token: Token): Future[ResultSet] = {
    insert
      .value(_.id, token.id)
      .value(_.tokenValue, token.tokenValue)
      .future()
  }

  def getTokenById(id: String): Future[Option[Token]] = {
    select.where(_.id eqs id).one()
  }
  def getAllRoles: Future[Seq[Token]] = {
    select.where(_.id).fetchEnumerator() run Iteratee.collect()
  }

}
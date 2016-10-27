package repositories.Util

import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.Keys
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/**
  *id:String,value:String
  */
class KeysRepository extends CassandraTable[KeysRepository, Keys] {

  object id extends StringColumn(this) with PrimaryKey[String]

  object value extends StringColumn(this)


  override def fromRow(r: Row): Keys = {
    Keys(
  id(r),
  value(r)
  )
}
}

object KeysRepository extends KeysRepository with RootConnector {

  override lazy val tableName = "keys"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(keys: Keys): Future[ResultSet] = {
    insert
      .value(_.id, keys.id)
      .value(_.value, keys.value)
      .future()
  }

  def getKeyById(id: String): Future[Option[Keys]] = {
    select.where(_.id eqs id).one()
  }

  def getAllkeys: Future[Seq[Keys]] = {
    select.where(_.id).fetchEnumerator() run Iteratee.collect()
  }
}
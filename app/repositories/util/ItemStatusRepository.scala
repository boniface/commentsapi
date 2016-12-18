package repositories.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.ItemStatus
import org.joda.time.DateTime

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/16.
  * itemId: String,
  * date: DateTime,
  * status: String,
  * description: String
  */
class ItemStatusRepository extends CassandraTable[ItemStatusRepository, ItemStatus] {

  object itemId extends StringColumn(this) with PartitionKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object status extends StringColumn(this)

  object description extends StringColumn(this)

  override def fromRow(r: Row): ItemStatus = {
    ItemStatus(itemId(r), date(r), status(r), description(r))
  }
}

object ItemStatusRepository extends ItemStatusRepository with RootConnector {
  override lazy val tableName = "status"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(status: ItemStatus): Future[ResultSet] = {
    insert
      .value(_.itemId, status.itemId)
      .value(_.date, status.date)
      .value(_.status, status.status)
      .value(_.description, status.description)
      .future()
  }

  def getStatus(itemId: String): Future[Seq[ItemStatus]] = {
    select.where(_.itemId eqs itemId).fetchEnumerator() run Iteratee.collect()
  }
}


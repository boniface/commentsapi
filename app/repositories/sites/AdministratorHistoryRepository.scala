package repositories.sites

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.sites.AdministratorHistory
import com.websudos.phantom.reactivestreams._

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/24.
  */
class AdministratorHistoryRepository extends CassandraTable[AdministratorHistoryRepository, AdministratorHistory] {

  object emailId extends StringColumn(this) with PartitionKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object siteId extends StringColumn(this)

  object adminStatusId extends StringColumn(this)

  override def fromRow(r: Row): AdministratorHistory = {
    AdministratorHistory(siteId(r), emailId(r), adminStatusId(r), date(r))
  }
}

object AdministratorHistoryRepository extends AdministratorHistoryRepository with RootConnector {

  override lazy val tableName = "AdministratorHistory"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(administratorHistory: AdministratorHistory): Future[ResultSet] = {
    insert
      .value(_.siteId, administratorHistory.siteId)
      .value(_.emailId, administratorHistory.emailId)
      .value(_.adminStatusId, administratorHistory.adminStatusId)
      .value(_.date, administratorHistory.date)
      .future()
  }

  def getAdminStatus(emailId: String): Future[Seq[AdministratorHistory]] = {
    select.where(_.emailId eqs emailId).fetchEnumerator() run Iteratee.collect()
  }

}

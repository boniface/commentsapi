package repositories.sites

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.sites.Administrators

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/24.
  */
class AdministratorsRepository extends CassandraTable[AdministratorsRepository, Administrators] {

  object siteId extends StringColumn(this) with PartitionKey[String]

  object emailId extends StringColumn(this) with PrimaryKey[String]

  override def fromRow(r: Row): Administrators = {
    Administrators(siteId(r), emailId(r))
  }
}

object AdministratorsRepository extends AdministratorsRepository with RootConnector {

  override lazy val tableName = "administrators"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def saveAdministrator(administrators: Administrators): Future[ResultSet] = {
    insert
      .value(_.siteId, administrators.siteId)
      .value(_.emailId, administrators.emailId)
      .future()
  }

  def getSiteAdministrator(siteId: String, emailId: String): Future[Option[Administrators]]  = {
    select.where(_.siteId eqs siteId).and(_.emailId eqs emailId).one()
  }

  def getSiteAdministrators(siteId: String): Future[Seq[Administrators]] = {
    select.where(_.siteId eqs siteId)fetchEnumerator() run Iteratee.collect()
  }

}

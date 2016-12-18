package repositories.sites

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.sites.Site

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/24.
  */
class SiteRepository extends CassandraTable[SiteRepository, Site] {

  object siteId extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object url extends StringColumn(this)

  override def fromRow(r: Row): Site = {
    Site(
      siteId(r),
      name(r),
      url(r))
  }
}

object SiteRepository extends SiteRepository with RootConnector {
  override lazy val tableName = "sites"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def saveSite(site: Site): Future[ResultSet] = {
    insert
      .value(_.siteId, site.siteId)
      .value(_.name, site.name)
      .value(_.url, site.url)
      .future()
  }

  def getSiteById(siteId: String): Future[Option[Site]] = {
    select.where(_.siteId eqs siteId).one()
  }

  def getAllSites: Future[Seq[Site]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}

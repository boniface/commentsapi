package repositories.sites

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.sites.Site
import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/24.
  */
class SiteRepository extends CassandraTable[SiteRepository,Site]{

  object siteId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)  with PartitionKey[String]
  object url extends StringColumn(this) with PartitionKey[String]

  override def fromRow(r:Row):Site ={
    Site(siteId(r),name(r),url(r))
  }
}

object SiteRepository extends SiteRepository with RootConnector{
  override lazy val tableName = "sites"
  override implicit def space: KeySpace = DataConnection.keySpace
  override implicit def session: Session = DataConnection.session

  def saveSite(site: Site): Future[ResultSet]={
    insert
      .value(_.siteId,site.siteId)
      .value(_.name,site.name)
      .value(_.url,site.url)
      .future()
  }
  def getSiteByName(siteId:String,name:String): Future[Option[Site]]={
    select.where(_.siteId eqs siteId).and(_.name eqs name).one()
  }

  def getSite(site:Site):Future[Option[Site]] = {
    select.where(_.siteId eqs site.siteId).and(_.name eqs site.name).and(_.url eqs site.url).one()
  }

  def getAllSites:Future[Seq[Site]]={
    select.all().fetch()
  }
}
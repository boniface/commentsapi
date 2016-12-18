package services.sites


import com.websudos.phantom.dsl.ResultSet
import domain.sites.Site
import domain.util.ItemStatus
import services.sites.impl.SitesServiceImpl

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/25.
  */
trait SitesService {

  def createOrUpdate(site: Site, status: ItemStatus): Future[ResultSet]

  def getSiteById(siteId: String): Future[Option[Site]]

  def getAllSites: Future[Seq[Site]]

  def getSiteStatus(siteId: String): Future[ItemStatus]

  def getSiteStatusHistory(siteId: String): Future[Seq[ItemStatus]]

}

object SitesService {
  def apply: SitesService = new SitesServiceImpl
}

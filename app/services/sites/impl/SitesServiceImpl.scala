package services.sites.impl

import com.websudos.phantom.dsl._
import domain.sites.Site
import domain.util.ItemStatus
import repositories.sites.SiteRepository
import repositories.util.ItemStatusRepository
import services.Service
import services.sites.SitesService


import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/25.
  */
class SitesServiceImpl extends SitesService with Service {

  override def createOrUpdate(site: Site, status: ItemStatus): Future[ResultSet] = {
    for {
      result <-SiteRepository.saveSite(site)
      result <-ItemStatusRepository.save(status)
    } yield result
  }

  override def getSiteById(siteId: String): Future[Option[Site]] = {
    SiteRepository.getSiteById(siteId)
  }

  override def getAllSites: Future[Seq[Site]] = {
    SiteRepository.getAllSites
  }

  override def getSiteStatus(siteId: String): Future[ItemStatus] = {
    ItemStatusRepository.getStatus(siteId) map (sites => sites.head)
  }

  override def getSiteStatusHistory(siteId: String): Future[Seq[ItemStatus]] = {
    ItemStatusRepository.getStatus(siteId)
  }

}

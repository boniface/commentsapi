package services.sites.impl

import com.websudos.phantom.dsl._
import domain.sites.Site
import repositories.sites.SiteRepository
import services.Service
import services.sites.SitesService

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/25.
  */
class SitesServiceImpl extends SitesService with Service{

  override def save(sites: Site): Future[ResultSet] = {
    val site = Site(sites.siteId,sites.name,sites.url)
    for {
      result <- SiteRepository.saveSite(site)
    }yield result
  }

  override def getSiteById(siteId:String,name:String): Future[Option[Site]] = {
    SiteRepository.getSiteByName(siteId,name)
  }


  override def getAllSites:Future[Seq[Site]] = {
    SiteRepository.getAllSites
  }

  override def updateSite(site: Site):Future[ResultSet] = {
    SiteRepository.saveSite(site)
  }
}

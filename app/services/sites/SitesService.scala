package services.sites

import com.websudos.phantom.dsl._
import domain.sites.Site
import services.Service
import services.sites.impl.SitesServiceImpl

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/25.
  */
trait SitesService {
  def save(site: Site): Future[ResultSet]
  def getSiteById(siteId:String): Future[Option[Site]]
  def getAllSites:Future[Seq[Site]]
  def updateSite(site: Site): Future[ResultSet]
}
object SitesService extends Service{
  def apply():SitesService = new SitesServiceImpl
}

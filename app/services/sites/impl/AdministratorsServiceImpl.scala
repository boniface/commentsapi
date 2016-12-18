package services.sites.impl

import com.websudos.phantom.dsl._
import domain.sites.{Administrators, SiteMessages}
import domain.util.ItemStatus
import repositories.sites.AdministratorsRepository
import repositories.util.ItemStatusRepository
import services.Service
import services.sites.AdministratorsService

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/29.
  */
class AdministratorsServiceImpl extends AdministratorsService with Service {
  override def saveAdministrator(administrators: Administrators): Future[ResultSet] = {
    val status = ItemStatus(administrators.emailId,new DateTime,SiteMessages.ACTIVE,SiteMessages.CREATED)
    for{
      result <-ItemStatusRepository.save(status)
      save<-  AdministratorsRepository.saveAdministrator(administrators)
    }yield save
  }

  override def getSiteAdministrator(siteId: String, emailId: String): Future[Option[Administrators]] = {
    AdministratorsRepository.getSiteAdministrator(siteId, emailId)
  }

  override def getSiteAdministrators(siteId: String): Future[Seq[Administrators]] = {
    AdministratorsRepository.getSiteAdministrators(siteId)
  }

  override def getSiteAdministratorStatus(emailId: String): Future[ItemStatus] = {
    ItemStatusRepository.getStatus(emailId) map( item => item.head)
  }

  override def getSiteAdministratorStatusHistory(emailId: String): Future[Seq[ItemStatus]] = {
    ItemStatusRepository.getStatus(emailId)
  }

  override def saveAdministratorStatus(status: ItemStatus): Future[ResultSet] = {
    ItemStatusRepository.save(status)
  }
}

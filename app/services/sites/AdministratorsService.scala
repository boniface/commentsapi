package services.sites

import com.websudos.phantom.dsl._
import domain.sites.Administrators
import domain.util.ItemStatus
import services.Service
import services.sites.impl.AdministratorsServiceImpl

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/29.
  */
trait AdministratorsService {

  def saveAdministrator(administrator: Administrators): Future[ResultSet]

  def getSiteAdministrator(siteId: String, emailId: String): Future[Option[Administrators]]

  def getSiteAdministrators(siteId: String): Future[Seq[Administrators]]

  def getSiteAdministratorStatus(emailId: String): Future[ItemStatus]

  def getSiteAdministratorStatusHistory(emailId: String): Future[Seq[ItemStatus]]

  def saveAdministratorStatus(status: ItemStatus): Future[ResultSet]
}

object AdministratorsService extends Service {
  def apply: AdministratorsService = new AdministratorsServiceImpl
}

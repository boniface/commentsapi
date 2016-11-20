package services.sites

import com.websudos.phantom.dsl._
import domain.sites.Administrators
import services.Service
import services.sites.impl.AdministratorsServiceImpl

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/29.
  */
trait AdministratorsService {
  def saveAdministrator(administrators: Administrators):Future[ResultSet]
  def getAdministratorsBySiteId(siteId:String,email:String):Future[Option[Administrators]]
  def deleteAdministratorBySiteId(siteId: String):Future[ResultSet]
  def getAdministrators: Future[List[Administrators]]
}

object AdministratorsService extends Service{
  def apply():AdministratorsService = new AdministratorsServiceImpl
}

package services.sites

import com.websudos.phantom.dsl._
import domain.sites.AdministratorHistory
import services.Service
import services.sites.impl.AdministratorHistoryServiceImpl

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/29.
  */
trait AdministratorHistoryService {

  def getAdministratorHistoryByDate(date:DateTime): Future[Option[AdministratorHistory]]
  def save(administratorHistory: AdministratorHistory):Future[ResultSet]
  def getAllAdministratorsHistory: Future[Seq[AdministratorHistory]]
  def deleteAll:Future[ResultSet]
}
object AdministratorHistoryService extends Service{
  def apply():AdministratorHistoryService = new AdministratorHistoryServiceImpl
}
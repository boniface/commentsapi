package services.sites.impl

import com.websudos.phantom.dsl._
import domain.sites.AdministratorHistory
import repositories.sites.AdministratorHistoryRepository
import services.Service
import services.sites.AdministratorHistoryService

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/29.
  */
class AdministratorHistoryServiceImpl extends AdministratorHistoryService with Service{

  override def getAdministratorHistoryByDate(ondate:DateTime): Future[Option[AdministratorHistory]] = {
    AdministratorHistoryRepository.getAdministratorHistoryByDate(ondate)
  }

  override def save(administratorHistory: AdministratorHistory):Future[ResultSet] = {
    val historyService = AdministratorHistory(administratorHistory.siteId,
      administratorHistory.emailId,
      administratorHistory.adminStatusId,
      administratorHistory.date)
    for {
      result <- AdministratorHistoryRepository.save(historyService)
    }yield result
  }

  override def getAllAdministratorsHistory: Future[Seq[AdministratorHistory]] = {
    AdministratorHistoryRepository.getAllAdministratorsHistory
  }

  override def deleteAll:Future[ResultSet] = {
    AdministratorHistoryRepository.deleteAll
  }
}

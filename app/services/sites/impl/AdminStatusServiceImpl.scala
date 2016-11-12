package services.sites.impl

import com.websudos.phantom.dsl._
import domain.sites.AdminStatus
import repositories.sites.AdminStatusRepository
import services.Service
import services.sites.AdminStatusService

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/29.
  */
class AdminStatusServiceImpl  extends AdminStatusService with Service{

  override def save(adminStatus: AdminStatus):Future[ResultSet] = {
    val adminStatusService = AdminStatus(adminStatus.adminStatusId,adminStatus.name)
    for{
      result <-AdminStatusRepository.save(adminStatus)
    }yield result
  }

  override def getAdminStatusById(adminStatusId:String):Future[Option[AdminStatus]] = {
    AdminStatusRepository.getAdminStatusById(adminStatusId)
  }
  override def deleteById(adminStatusId: String):Future[ResultSet]= {
    AdminStatusRepository.deleteById(adminStatusId)
  }
  override def getAllAdminStatus: Future[Seq[AdminStatus]]= {
    AdminStatusRepository.getAllAdminStatus
  }
}

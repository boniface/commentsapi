package services.sites

import com.websudos.phantom.dsl._
import domain.sites.AdminStatus
import services.Service
import services.sites.impl.AdminStatusServiceImpl

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/29.
  */
trait AdminStatusService {
  def save(adminStatus: AdminStatus):Future[ResultSet]
  def getAdminStatusById(adminStatusId:String):Future[Option[AdminStatus]]
  def deleteById(adminStatusId: String):Future[ResultSet]
  def getAllAdminStatus: Future[Seq[AdminStatus]]
}
object AdminStatusService extends Service{
  def apply():AdminStatusService = new AdminStatusServiceImpl
}


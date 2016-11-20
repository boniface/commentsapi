package services.sites.impl

import com.websudos.phantom.dsl._
import domain.sites.Administrators
import repositories.sites.AdministratorsRepository
import services.Service
import services.sites.AdministratorsService

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/29.
  */
class AdministratorsServiceImpl extends AdministratorsService with Service{

  override def saveAdministrator(administrators: Administrators):Future[ResultSet]= {
    val administratorsService = Administrators(administrators.siteId,administrators.emailId)
    for{
      result<- AdministratorsRepository.saveAdministrator(administrators)
    } yield result
  }

  override def getAdministratorsBySiteId(siteId:String,email:String):Future[Option[Administrators]]= {
    AdministratorsRepository.getAdministratorsBySiteId(siteId,email)
  }

  override def deleteAdministratorBySiteId(siteId: String):Future[ResultSet]= {
    AdministratorsRepository.deleteAdministratorBySiteId(siteId)
  }
  override def getAdministrators: Future[List[Administrators]]= {
    AdministratorsRepository.getAdministrators
  }

}

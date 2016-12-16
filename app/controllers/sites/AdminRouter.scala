package controllers.sites

import javax.inject.Inject

import org.joda.time.DateTime
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by hashcode on 2016/12/14.
  */
class AdminRouter@Inject()
(sitesController: SitesController)
(adminStatusController: AdminStatusController)
(administratorsController: AdministratorsController)
(administratorHistoryController: AdministratorHistoryController)
 extends SimpleRouter{
  override def routes: Routes = {
    //Sites
    case POST(p"/site/create") =>
      sitesController.createOrUpdate
    case GET(p"/site/get/$siteId") =>
      sitesController.getSite(siteId)
    case GET(p"/site/all") =>
      sitesController.getAllSites

    // Admin Status

    case POST(p"/status/create") =>
      adminStatusController.createOrUpdate
    case GET(p"/status/get/$adminStatusId") =>
      adminStatusController.getAdminStatusById(adminStatusId)
    case GET(p"/status/all") =>
      adminStatusController.getAllAdminStatus


    // AdminHistory

    case POST(p"/history/create") =>
      administratorHistoryController.createOrUpdate
    case GET(p"/history/get/$date") =>
      administratorHistoryController.getAdministratorHistoryByDate(new DateTime(date))
    case GET(p"/history/all") =>
      administratorHistoryController.getAllAdministratorsHistory


    // Administrators


    case POST(p"/create") =>
      administratorsController.createOrUpdate
    case GET(p"/get/$siteId,$email") =>
      administratorsController.getAdministratorsBySiteId(siteId,email)
    case GET(p"/all") =>
      administratorsController.getAdministrators

  }



}

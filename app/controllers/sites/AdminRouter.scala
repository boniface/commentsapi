package controllers.sites

import javax.inject.Inject

import org.joda.time.DateTime
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by hashcode on 2016/12/14.
  */
class AdminRouter @Inject()
(sitesController: SitesController)
(administratorsController: AdministratorsController)
  extends SimpleRouter {
  override def routes: Routes = {
    //Sites
    case POST(p"/site/create") =>
      sitesController.create
    case GET(p"/site/save") =>
      sitesController.save
    case GET(p"/site/all") =>
      sitesController.getAllSites
    case POST(p"/site/status/$siteId") =>
      sitesController.getSiteStatus(siteId)
    case GET(p"/site/get/$siteId") =>
      sitesController.getSite(siteId)
    case GET(p"/site/status/history/$siteId") =>
      sitesController.getSiteStatusHistory(siteId)
    case POST(p"/site/activate/$siteId") =>
      sitesController.activateSite(siteId)
    case GET(p"/site/disable/$siteId") =>
      sitesController.disableSite(siteId)

    // Administrators

    case POST(p"/create") =>
      administratorsController.createOrUpdate
    case GET(p"/administrators/$siteId") =>
      administratorsController.getSiteAdministrators(siteId)
    case GET(p"/administrator/$siteId/$emaildId") =>
      administratorsController.getSiteAdministrator(siteId, emaildId)
    case GET(p"/activate/$siteId/$emaildId") =>
      administratorsController.activatedAdministrator(siteId, emaildId)
    case GET(p"/revoke/$siteId/$emaildId") =>
      administratorsController.revokeAdminRights(siteId, emaildId)
    case GET(p"/admin/status/$emailId") =>
      administratorsController.getSiteAdministratorStatus(emailId)
    case GET(p"/admin/history/$emailId") =>
      administratorsController.getSiteAdministratorStatusHistory(emailId)

  }

}

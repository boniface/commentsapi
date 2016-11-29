package controllers.sites

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by hashcode on 2016/09/23.
  */
class SiteRouter @Inject()
(sites: SitesController)
  extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/get/$siteId") =>
      sites.getSite(siteId)
    case GET(p"/get/all") =>
      sites.getAllSites
    case POST(p"/create") =>
      sites.createOrUpdate
  }
}

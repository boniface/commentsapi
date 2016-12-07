package controllers.sites

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Quest on 2016/12/01.
  */
class AdministratorsRouter @Inject()(administrators: AdministratorsController) extends SimpleRouter{

  override def routes: Routes = {
    case GET(p"/get/siteId") =>
      administrators.getAdministratorsBySiteId("siteId","email")
    case GET(p"/get/all") =>
      administrators.getAdministrators
    case POST(p"/create") =>
      administrators.createOrUpdate
    case DELETE(p"/del/siteId") =>
      administrators.deleteAdministratorBySiteId("siteId")
  }
}

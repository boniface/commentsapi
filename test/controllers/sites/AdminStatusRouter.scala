package controllers.sites

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Quest on 2016/12/01.
  */
class AdminStatusRouter @Inject()
  (adminStatus: AdminStatusController)
  extends SimpleRouter {
    override def routes: Routes = {
      case GET(p"/get/adminStatusId") =>
        adminStatus.getAdminStatus("adminStatusId")
      case GET(p"/get/all") =>
        adminStatus.getAllAdminStatus
      case POST(p"/create") =>
        adminStatus.createOrUpdate
      case DELETE(p"/del/adminStatusId") =>
        adminStatus.deleteAdminStatus("adminStatusId")
    }
}

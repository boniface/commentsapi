package controllers.sites

import javax.inject.Inject

import org.joda.time.DateTime
import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Quest on 2016/12/01.
  */
class AdministratorHistoryRouter @Inject()(admin: AdministratorHistoryController) extends SimpleRouter{

  override def routes: Routes = {
    case GET(p"/get/date") =>
      admin.getAdministratorHistoryByDate(new DateTime())
    case GET(p"/get/all") =>
      admin.getAllAdministratorsHistory
    case POST(p"/create") =>
      admin.createOrUpdate
    case DELETE(p"/del/all") =>
      admin.deleteAllHistory
  }
}

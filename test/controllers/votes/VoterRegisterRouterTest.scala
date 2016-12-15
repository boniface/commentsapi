package controllers.votes

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by fatimam on 07/12/2016.
  */
class VoterRegisterRouterTest @Inject()(voterRegisterController: VoterRegisterController) extends SimpleRouter
{
  override def routes: Routes = {
    case GET(p"/voterRegister/get/$id") =>
      voterRegisterController.get(id="1")
    case GET(p"/voterRegister/get/all") =>
      voterRegisterController.getAll
    case POST(p"/voterRegister/create") =>
      voterRegisterController.saveOrUpdate
  }

}

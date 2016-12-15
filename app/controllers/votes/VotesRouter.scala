package controllers.votes
import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by fatimam on 15/12/2016.
  */
class VotesRouter @Inject()
(voteUpController: VotesUpController)
(voterRegisterController: VoterRegisterController)
(voteDownController: VotesDownController) extends SimpleRouter
{
  override def routes: Routes = {

    //------VotesUp------
    case GET(p"/voteUp/get/$id") =>
      voteUpController.get(id)
    case GET(p"/voteUp/get/all") =>
      voteUpController.getAll
    case POST(p"/voteUp/create") =>
      voteUpController.saveOrUpdate

    //------VotersRegister------
    case GET(p"/voterRegister/get/$id") =>
      voterRegisterController.get(id)
    case GET(p"/voterRegister/get/all") =>
      voterRegisterController.getAll
    case POST(p"/voterRegister/create") =>
      voterRegisterController.saveOrUpdate

    //------VotesDown------
    case GET(p"/voteDown/get/$id") =>
      voteDownController.get(id)
    case GET(p"/voteDown/get/all") =>
      voteDownController.getAll
    case POST(p"/voteDown/create") =>
      voteDownController.saveOrUpdate
  }

}

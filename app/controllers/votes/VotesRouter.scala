package controllers.votes
import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by fatimam on 15/12/2016.
  */
class VotesRouter @Inject()
(votesController: VotesController) extends SimpleRouter
{
  override def routes: Routes = {

    //------VotesUp------
    case GET(p"/voteUp/get/$id") =>
      votesController.get(id)
  }

}

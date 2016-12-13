package controllers.votes

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by fatimam on 07/12/2016.
  */
class VoteUpRouter @Inject() (voteUp: VotesUpController) extends SimpleRouter
{
  override def routes: Routes = {
    case GET(p"/get/voteDown") =>
      voteUp.get(id="1")
    case GET(p"/get/all") =>
      voteUp.getAll
    case POST(p"/create") =>
      voteUp.saveOrUpdate
  }

}

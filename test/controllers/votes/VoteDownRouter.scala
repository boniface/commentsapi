package controllers.votes

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by fatimam on 07/12/2016.
  */
class VoteDownRouter @Inject() (voteDown: VotesDownController) extends SimpleRouter
{
  override def routes: Routes = {
    case GET(p"/get/voteDown") =>
      voteDown.get(id="1")
    case GET(p"/get/all") =>
      voteDown.getAll
    case POST(p"/create") =>
      voteDown.saveOrUpdate
  }
}

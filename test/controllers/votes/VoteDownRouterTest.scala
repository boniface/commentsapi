package controllers.votes

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by fatimam on 07/12/2016.
  */
class VoteDownRouterTest @Inject()(voteDownController: VotesDownController) extends SimpleRouter
{
  override def routes: Routes = {
    case GET(p"/voteDown/get/$id") =>
      voteDownController.get(id="1")
    case GET(p"/get/all") =>
      voteDownController.getAll
    case POST(p"/voteDown/create") =>
      voteDownController.saveOrUpdate
  }
}

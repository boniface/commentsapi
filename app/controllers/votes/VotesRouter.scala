package controllers.votes

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by fatimam on 15/12/2016.
  */
class VotesRouter @Inject()
(votesController: VotesController) extends SimpleRouter {
  override def routes: Routes = {


    case GET(p"/vote/comment/down/$siteId/$itemId") =>
      votesController.commentDownVote(siteId, itemId)
    case GET(p"/vote/comment/up/$siteId/$itemId") =>
      votesController.commentUpVote(siteId, itemId)
    case GET(p"/vote/down/$siteId/$itemId") =>
      votesController.getDownVotes(siteId, itemId)
    case GET(p"/vote/up/$siteId/$itemId") =>
      votesController.getUpVotes(siteId, itemId)
    case GET(p"/vote/user/down/$siteId/$itemOwnerId") =>
      votesController.getUserDownVotes(siteId, itemOwnerId)
    case GET(p"/vote/user/up/$siteId/$itemOwnerId") =>
      votesController.getUserUpVotes(siteId, itemOwnerId)
    case GET(p"/vote/response/up/$siteId/$itemId") =>
      votesController.responseUpVote(siteId, itemId)
    case GET(p"/vote/response/down/$siteId/$itemId") =>
      votesController.responseDownVote(siteId, itemId)

  }

}

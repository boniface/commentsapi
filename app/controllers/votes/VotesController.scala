package controllers.votes

import domain.votes.{VoteDown, VoteUp}
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.comments.{CommentService, ResponseService}
import services.votes.VoteService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/24.
  */
class VotesController extends Controller {

  // Upvotes
  def commentUpVote(siteId: String, itemId: String) = Action.async {
    request =>

      val response = for {
        item <- CommentService.apply().getComment(itemId)
        results <- VoteService.apply.castUpVote(VoteUp(siteId, itemId, request.remoteAddress, item.get.emailId, new DateTime))
      } yield results

      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def responseUpVote(siteId: String, itemId: String) = Action.async {
    request =>

      val response = for {
        item <- ResponseService.apply().getResponse(itemId)
        results <- VoteService.apply.castUpVote(VoteUp(siteId, itemId, request.remoteAddress, item.get.emailId, new DateTime))
      } yield results

      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getUserUpVotes(siteId: String, itemOwnerId: String) = Action.async {
    request =>
      val response = for {
        results <- VoteService.apply.getUserUpVotes(siteId, itemOwnerId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getUpVotes(siteId: String, itemId: String) = Action.async {
    request =>
      val response = for {
        results <- VoteService.apply.getUpVotes(siteId, itemId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  // DownVotes
  def commentDownVote(siteId: String, itemId: String)= Action.async {
    request =>

      val response = for {
        item <- CommentService.apply().getComment(itemId)
        results <- VoteService.apply.castDownVote(VoteDown(siteId, itemId, request.remoteAddress, item.get.emailId, new DateTime))
      } yield results

      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def responseDownVote(siteId: String, itemId: String)= Action.async {
    request =>

      val response = for {
        item <- ResponseService.apply().getResponse(itemId)
        results <- VoteService.apply.castDownVote(VoteDown(siteId, itemId, request.remoteAddress, item.get.emailId, new DateTime))
      } yield results

      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


  def getUserDownVotes(siteId: String, itemOwnerId: String) = Action.async {
    request =>
      val response = for {
        results <- VoteService.apply.getUserDownVotes(siteId, itemOwnerId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

  def getDownVotes(siteId: String, itemId: String) = Action.async {
    request =>
      val response = for {
        results <- VoteService.apply.getDownVotes(siteId, itemId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }


}

package services.votes

import com.websudos.phantom.reactivestreams.Iteratee
import domain.votes.{VoteDown, VoteUp}
import repositories.votes.UserUpVotesRepository.select
import services.votes.impl.VoteServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/24.
  */
trait VoteService {

  // Upvotes
  def castUpVote(vote: VoteUp): Future[Seq[VoteUp]]

  def getUserUpVotes(itemOwnerId: String): Future[Seq[VoteDown]]

  def getUpVotes(itemId: String): Future[Seq[VoteUp]]



  // DownVotes
  def castDownVote(vote: VoteDown): Future[Seq[VoteDown]]

  def getUserDownVotes(itemOwnerId: String): Future[Seq[VoteDown]]

  def getDownVotes(itemId: String): Future[Seq[VoteDown]]
}

object VoteService{
  def apply: VoteService = new VoteServiceImpl()
}

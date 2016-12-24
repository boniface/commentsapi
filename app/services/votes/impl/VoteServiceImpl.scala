package services.votes.impl

import domain.votes.{VoteDown, VoteUp}
import repositories.votes.{UserDownVotesRepository, UserUpVotesRepository, VoteDownRepository, VoteUpRepository}
import services.Service
import services.votes.VoteService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/12/24.
  */
class VoteServiceImpl extends VoteService with Service {
  // Upvotes
  override def castUpVote(vote: VoteUp): Future[Seq[VoteUp]] = {
    //Check Voter Register First
    val checkvote = for {
      upVote <- VoteUpRepository.getVoteId(vote.siteId,vote.itemId, vote.ipAddress)
      downVote <- VoteDownRepository.getVoteId(vote.siteId,vote.itemId, vote.ipAddress)
    } yield (upVote, downVote)

    checkvote map (voteresult => {

      voteresult match {

        case (Some(upVote), None) =>
          for {
            deleteVote <- VoteUpRepository.deleteVote(vote.siteId,upVote.itemId, upVote.ipAddress)
            deleteVote <- UserUpVotesRepository.deleteVote(vote.siteId,upVote.itemOwnerId, upVote.itemId, upVote.ipAddress)
          } yield deleteVote

        case (None, Some(downVote)) =>
          for {
            deleteVote <- VoteDownRepository.deleteVote(downVote.siteId,downVote.itemId, downVote.ipAddress)
            deleteVote <- UserDownVotesRepository.deleteVote(downVote.siteId,downVote.itemOwnerId, downVote.itemId, downVote.ipAddress)
            castVote <- VoteUpRepository.save(vote)
            castVote <- UserUpVotesRepository.save(vote)
          } yield deleteVote

        case (Some(upVote), Some(downVote)) => None

        case (None, None) =>
          for {
            castVote <- VoteUpRepository.save(vote)
            castVote <- UserUpVotesRepository.save(vote)
          } yield castVote
      }
    })
    VoteUpRepository.getVotes(vote.siteId,vote.itemId)
  }

  override def getUserUpVotes(siteId:String,itemOwnerId: String): Future[Seq[VoteUp]] = {
    UserUpVotesRepository.getUserVotes(siteId,itemOwnerId)
  }

  override def getUpVotes(siteId:String,itemId: String): Future[Seq[VoteUp]] = {
    VoteUpRepository.getVotes(siteId,itemId)
  }

  // DownVotes
  override def castDownVote(vote: VoteDown): Future[Seq[VoteDown]] = {
    //Check Voter Register First
    val checkvote = for {
      upVote <- VoteUpRepository.getVoteId(vote.siteId,vote.itemId, vote.ipAddress)
      downVote <- VoteDownRepository.getVoteId(vote.siteId,vote.itemId, vote.ipAddress)
    } yield (upVote, downVote)

    checkvote map (voteresult => {
      voteresult match {

        case (Some(upVote), None) =>
          for {
            deleteVote <- VoteUpRepository.deleteVote(upVote.siteId,upVote.itemId, upVote.ipAddress)
            deleteVote <- UserUpVotesRepository.deleteVote(upVote.siteId,upVote.itemOwnerId, upVote.itemId, upVote.ipAddress)
            castDownVoteVote <- VoteDownRepository.save(vote)
            castDownVoteVote <- UserDownVotesRepository.save(vote)
          } yield deleteVote

        case (None, Some(downVote)) =>
          for {
            deleteVote <- VoteDownRepository.deleteVote(downVote.siteId,downVote.itemId, downVote.ipAddress)
            deleteVote <- UserDownVotesRepository.deleteVote(downVote.siteId,downVote.itemOwnerId, downVote.itemId, downVote.ipAddress)
          } yield deleteVote

        case (Some(upVote), Some(downVote)) => None

        case (None, None) =>
          for {
            castDownVoteVote <- VoteDownRepository.save(vote)
            castDownVoteVote <- UserDownVotesRepository.save(vote)
          } yield castDownVoteVote
      }
    })
    VoteDownRepository.getVotes(vote.siteId,vote.itemId)
  }

  override def getUserDownVotes(siteId:String,itemOwnerId: String): Future[Seq[VoteDown]] = {
    UserDownVotesRepository.getUserVotes(siteId,itemOwnerId)
  }

  override def getDownVotes(siteId:String,itemId: String): Future[Seq[VoteDown]] = {
    VoteDownRepository.getVotes(siteId,itemId)
  }
}

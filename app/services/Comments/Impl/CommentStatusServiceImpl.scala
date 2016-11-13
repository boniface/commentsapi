package services.Comments.Impl

import com.datastax.driver.core.ResultSet
import domain.comments.{CommentStatus, Comment}
import repositories.comments.{CommentStatusRepository, CommentRepository}
import services.Comments.{CommentStatusService, CommentService}
import services.Service

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
class CommentStatusServiceImpl extends CommentStatusService with Service {

  override def getCommentStatusByCommentId(id: String): Future[Option[CommentStatus]] = {
    CommentStatusRepository.getSiteCommentStatus(id)
  }

  override def save(commentstatus: CommentStatus): Future[ResultSet] = {
    val commentstatusService = CommentStatus(commentstatus.commentId,
      commentstatus.status,
      commentstatus.date)
    for {
      result <- CommentRepository.save(commentStatusService)
    } yield result
  }

  override def getAllCommentStatus: Future[Seq[CommentStatus]] = {
    CommentStatusRepository.getAllCommentStatus
  }

  override def deleteAll: Future[ResultSet] = {
    CommentStatusRepository.deleteAll
  }


}

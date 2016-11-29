package services.comments.Impl

import com.datastax.driver.core.ResultSet
import domain.comments.CommentStatus
import repositories.comments.CommentStatusRepository
import services.Service
import services.comments.CommentStatusService

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
class CommentStatusServiceImpl extends CommentStatusService with Service {

  override def getCommentStatusBySubjectId(id: String): Future[Option[CommentStatus]] = {
    CommentStatusRepository.getSiteCommentStatus(id)
  }

  override def save(commentstatus: CommentStatus): Future[ResultSet] = {
    val commentstatusService = CommentStatus(commentstatus.commentId,
      commentstatus.status,
      commentstatus.date)
    for {
      result <- CommentStatusRepository.save(commentstatusService)
    } yield result
  }

  override def getAllCommentStatus: Future[Seq[CommentStatus]] = {
    CommentStatusRepository.getAllCommentStatus
  }

  override def deleteAll: Future[ResultSet] = {
    CommentStatusRepository.deleteAll
  }


}

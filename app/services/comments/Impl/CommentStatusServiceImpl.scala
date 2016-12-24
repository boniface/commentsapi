package services.comments.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.comments.CommentStatus
import repositories.comments.CommentStatusRepository
import services.Service
import services.comments.CommentStatusService

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
class CommentStatusServiceImpl extends CommentStatusService with Service {
  override def save(commentStatus: CommentStatus): Future[ResultSet] = {
    CommentStatusRepository.save(commentStatus)
  }

  override def getCommentStatus(commentsId: String): Future[Seq[CommentStatus]] = {
    CommentStatusRepository.getCommentStatus(commentsId)
  }
}

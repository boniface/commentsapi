package services.Comments.Impl
import com.datastax.driver.core.ResultSet
import domain.comments.{Abuse, CommentStatus, Comment}
import repositories.comments.{AbuseRepository, CommentStatusRepository, CommentRepository}
import services.Comments.{CommentStatusService, CommentService}
import services.Service

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
class CommentStatusServiceImpl extends CommentStatusService with Service {

  override def getCommentStatusBySubjectId(id: String): Future[Option[Abuse]] = {
    AbuseRepository.getSiteAbuse(id)
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

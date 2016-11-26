package services.comments.Impl

import com.datastax.driver.core.ResultSet
import domain.comments.Comment
import repositories.comments.CommentRepository
import services.comments.CommentService
import services.Service

import scala.concurrent.Future

/**
  * Created by Bonga on 10/29/2016.
  */
class CommentServiceImpl extends CommentService with Service {

  override def getCommentBySubjectId(id: String): Future[Option[Comment]] = {
    CommentRepository.getSiteComment(id)
  }

  override def save(comment: Comment): Future[ResultSet] = {
    val commentService = Comment(comment.subjectId,
      comment.siteId,
      comment.commentId,
      comment.emailId,
      comment.ipaddress,
      comment.comment,
      comment.date)
    for {
      result <- CommentRepository.save(commentService)
    } yield result
  }

  override def getAllComment: Future[Seq[Comment]] = {
    CommentRepository.getAllComment
  }

  override def deleteAll: Future[ResultSet] = {
    CommentRepository.deleteAll
  }


}

package services.comments.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.comments.Comment
import repositories.comments.{CommentRepository, CommentsByUserRepository, SingleCommentRepository}
import services.Service
import services.comments.CommentService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Bonga on 10/29/2016.
  */
class CommentServiceImpl extends CommentService with Service {
  override def saveComment(comment: Comment): Future[ResultSet] = {
    for{
      save <-CommentRepository.save(comment)
      save <- CommentsByUserRepository.save(comment)
      save <- SingleCommentRepository.save(comment)
    } yield save

  }

  override def getSiteComments(siteId: String): Future[Seq[Comment]] = {
    CommentRepository.getSiteComments(siteId)
  }

  override def getSubjectComments(siteId: String, subjectId: String): Future[Seq[Comment]] = {
    CommentRepository.getSubjectComments(siteId,subjectId)
  }

  override def getComment(commentId: String): Future[Option[Comment]] = {
    SingleCommentRepository.getComment(commentId)
  }

  override def getUserComments(siteId: String, emailId: String): Future[Seq[Comment]] = {
    CommentsByUserRepository.getUserComments(siteId,emailId)
  }
}

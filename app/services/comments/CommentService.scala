package services.comments

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl.ResultSet
import com.websudos.phantom.reactivestreams.Iteratee
import domain.comments.Comment
import repositories.comments.CommentRepository.{insert, select}
import repositories.comments.CommentsByUserRepository.select
import services.Service
import services.comments.Impl.CommentServiceImpl

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
trait CommentService {

  def saveComment(comment: Comment): Future[ResultSet]

  def getSiteComments(siteId: String): Future[Seq[Comment]]

  def getSubjectComments(siteId: String, subjectId: String): Future[Seq[Comment]]

  def getComment(siteId: String, subjectId: String, commentId: String): Future[Option[Comment]]

  def getUserComments(siteId: String, emailId: String): Future[Seq[Comment]]


}

object CommentService {
  def apply(): CommentService = new CommentServiceImpl
}
package services.comments

import com.websudos.phantom.dsl.ResultSet
import domain.comments.CommentStatus
import services.comments.Impl.CommentStatusServiceImpl

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
trait CommentStatusService {
  def save(commentStatus: CommentStatus): Future[ResultSet]

  def getCommentStatus(commentsId: String): Future[Seq[CommentStatus]]
}

object CommentStatusService {
  def apply(): CommentStatusService = new CommentStatusServiceImpl

}

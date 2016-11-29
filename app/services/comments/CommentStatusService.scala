package services.comments

import com.datastax.driver.core.ResultSet
import domain.comments.CommentStatus
import services.Service
import services.comments.Impl.CommentStatusServiceImpl

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
trait CommentStatusService {


  def getCommentStatusBySubjectId(id:String): Future[Option[CommentStatus]]
  def save(commentstatus: CommentStatus):Future[ResultSet]
  def getAllCommentStatus: Future[Seq[CommentStatus]]
  def deleteAll:Future[ResultSet]
}

object CommentStatusService extends Service {
  def apply(): CommentStatusService = new CommentStatusServiceImpl

}

package services.comments

import com.websudos.phantom.dsl.ResultSet
import domain.comments.Response
import services.comments.Impl.ResponseServiceImpl

import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
trait ResponseService {


  def save(response: Response): Future[ResultSet]

  def getCommentResponses(commentId: String): Future[Seq[Response]]


  def getResponse(commentId: String, responseId: String): Future[Option[Response]]

  def getUserResponses(emailId: String): Future[Seq[Response]]


}

object ResponseService {
  def apply(): ResponseService = new ResponseServiceImpl
}

package services.comments.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.comments.Response
import repositories.comments.{ResponseByUserRepository, ResponseRepository, SingleResponseRepository}
import services.Service
import services.comments.ResponseService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
class ResponseServiceImpl  extends ResponseService with Service{
  override def save(response: Response): Future[ResultSet] = {
    for{
      save <-ResponseRepository.save(response)
      save <- ResponseByUserRepository.save(response)
      save <-SingleResponseRepository.save(response)
    } yield save
  }

  override def getCommentResponses(commentId: String): Future[Seq[Response]] = {
    ResponseRepository.getCommentResponses(commentId)
  }

  override def getResponse(responseId: String): Future[Option[Response]] = {
    SingleResponseRepository.getResponse(responseId)
  }

  override def getUserResponses(emailId: String): Future[Seq[Response]] = {
    ResponseByUserRepository.getUserResponses(emailId)
  }
}

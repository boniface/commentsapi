package services.Comments.Impl
import com.datastax.driver.core.ResultSet
import domain.comments.Response
import repositories.comments.ResponseRepository
import services.Comments.ResponseService
import services.Service
import scala.concurrent.Future
/**
  * Created by Bonga on 11/13/2016.
  */
class ResponseServiceImpl  extends ResponseService with Service{

  override def getResponseByCommentId(id: String): Future[Option[Response]] = {
    ResponseRepository.getSiteResponse(id)
  }

  override def save(response: Response):Future[ResultSet] = {
    val responseService = Response(response.commentId,
      response.responseId,
      response.response,
      response.emailId,
      response.ipaddress,
      response.date)
    for {
      result <- ResponseRepository.save(responseService)
    } yield result
  }

  override def getAllResponse: Future[Seq[Response]] = {
    ResponseRepository.getAllResponse
  }

  override def deleteAll: Future[ResultSet] = {
    ResponseRepository.deleteAll
  }

}

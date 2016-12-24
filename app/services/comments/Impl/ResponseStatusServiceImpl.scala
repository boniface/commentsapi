package services.comments.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.comments.ResponseStatus
import repositories.comments.ResponseStatusRepository
import services.Service
import services.comments.ResponseStatusService

import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
class ResponseStatusServiceImpl  extends ResponseStatusService with Service{

  override def save(responseStatus: ResponseStatus): Future[ResultSet] = {
    ResponseStatusRepository.save(responseStatus)
  }

  override def getResponseStatus(responseId: String): Future[Seq[ResponseStatus]] = {
    ResponseStatusRepository.getResponseStatus(responseId)
  }
}

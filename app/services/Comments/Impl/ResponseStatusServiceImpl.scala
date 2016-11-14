package services.comments.Impl
import com.datastax.driver.core.ResultSet
import domain.comments.ResponseStatus
import repositories.comments.{AbuseRepository, ResponseStatusRepository}
import services.comments.ResponseStatusService
import services.Service
import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
class ResponseStatusServiceImpl  extends ResponseStatusService with Service{


  override def getResponseStatusBySubjectId(id: String): Future[Option[ResponseStatus]] = {
    ResponseStatusRepository.getSiteResponseStatus(id)
  }
  override def save(responsestatus: ResponseStatus): Future[ResultSet] = {
    val responsestatusService = ResponseStatus(responsestatus.responseId,
      responsestatus.status,
      responsestatus.date)
    for {
      result <- ResponseStatusRepository.save(responsestatusService)
    } yield result
  }

  override def getAllResponseStatus: Future[Seq[ResponseStatus]] = {
    ResponseStatusRepository.getAllResponseStatus
  }

  override def deleteAll: Future[ResultSet] = {
    ResponseStatusRepository.deleteAll
  }

}

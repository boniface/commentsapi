package services.Comments.Impl

import com.datastax.driver.core.ResultSet
import domain.comments.{ResponseStatus, Abuse}
import repositories.comments.{ResponseStatusRepository, AbuseRepository}
import services.Comments.ResponseStatusService
import services.Service

import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
class ResponseStatusServiceImpl  extends ResponseStatusService with Service{

  override def getResponseStatusByResponseId(id: String): Future[Option[Responsestatus]] = {
    ResponseStatusRepository.getSiteResponseStatus(id)
  }
  override def save(responsestatus: responsestatus): Future[ResultSet] = {
    val responsestatusService = ResponseStatus(responsestatus.responsetId,
      responsestatus.status,
      responsestatus.date)
    for {
      result <- responsestatusRepository.save(responsestatusService)
    } yield result
  }

  override def getAllresponsestatus: Future[Seq[responsestatus]] = {
    responsestatusRepository.getAllAbuse
  }

  override def deleteAll: Future[ResultSet] = {
    responsestatusRepository.deleteAll
  }

}

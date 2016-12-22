package services.comments

import com.websudos.phantom.dsl.ResultSet
import domain.comments.ResponseStatus
import services.comments.Impl.ResponseStatusServiceImpl

import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
trait ResponseStatusService {

  def save(responseStatus: ResponseStatus): Future[ResultSet]
  def getResponseStatus(responseId: String): Future[Seq[ResponseStatus]]
}

object ResponseStatusService {
  def apply(): ResponseStatusService = new ResponseStatusServiceImpl
}
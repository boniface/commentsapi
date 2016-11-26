package services.comments

import com.datastax.driver.core.ResultSet
import domain.comments.ResponseStatus
import services.Service
import services.comments.Impl.ResponseStatusServiceImpl

import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
trait ResponseStatusService {


  def getResponseStatusBySubjectId(id:String): Future[Option[ResponseStatus]]
  def save(responseStatus: ResponseStatus):Future[ResultSet]
  def getAllResponseStatus: Future[Seq[ResponseStatus]]
  def deleteAll:Future[ResultSet]
}

object ResponseStatusService extends Service {
  def apply(): ResponseStatusService = new ResponseStatusServiceImpl
}
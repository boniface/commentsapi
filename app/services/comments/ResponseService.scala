package services.comments

import com.datastax.driver.core.ResultSet
import domain.comments.Response
import services.Service
import services.comments.Impl.ResponseServiceImpl

import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
trait ResponseService {


  def getResponseByCommentId(id:String): Future[Option[Response]]
  def save(response: Response):Future[ResultSet]
  def getAllResponse: Future[Seq[Response]]
  def deleteAll:Future[ResultSet]
}

object ResponseService extends Service {
  def apply(): ResponseService = new ResponseServiceImpl
}

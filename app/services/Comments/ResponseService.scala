package services.Comments
import com.datastax.driver.core.ResultSet
import com.sun.deploy.nativesandbox.comm.Response
import services.Comments.Impl.{ResponseServiceImpl, AbuseServiceImpl}
import services.Service
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

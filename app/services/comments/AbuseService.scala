package services.comments

import com.websudos.phantom.dsl.ResultSet
import domain.comments.Abuse
import services.comments.Impl.AbuseServiceImpl

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
trait AbuseService {


  def save(abuse: Abuse): Future[ResultSet]

  def getItemAbuse(siteId: String, commentIdOrResponseId: String): Future[Seq[Abuse]]

  def getUserAbusiveComments(siteId: String, emailId: String): Future[Seq[Abuse]]
}

object AbuseService {
  def apply(): AbuseService = new AbuseServiceImpl
}
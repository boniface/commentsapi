package services.comments.Impl

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl.ResultSet
import domain.comments.Abuse
import repositories.comments.AbuseRepository
import services.Service
import services.comments.AbuseService

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
class AbuseServiceImpl extends AbuseService with Service{
  override def save(abuse: Abuse): Future[ResultSet] = ???

  override def getItemAbuse(siteId: String, commentIdOrResponseId: String): Future[Seq[Abuse]] = ???

  override def getUserAbusiveComments(siteId: String, commentIdOrResponseId: String, emailId: String): Future[Seq[Abuse]] = ???
}

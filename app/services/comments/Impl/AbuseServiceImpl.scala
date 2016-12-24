package services.comments.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.comments.Abuse
import repositories.comments.{AbuseByUserRepository, AbuseRepository}
import services.Service
import services.comments.AbuseService

import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
class AbuseServiceImpl extends AbuseService with Service{
  override def save(abuse: Abuse): Future[ResultSet] = {
    AbuseRepository.save(abuse)
  }

  override def getItemAbuse(siteId: String, commentIdOrResponseId: String): Future[Seq[Abuse]] = {
    AbuseRepository.getItemAbuse(siteId,commentIdOrResponseId)
  }

  override def getUserAbusiveComments(siteId: String, emailId: String): Future[Seq[Abuse]] = {
    AbuseByUserRepository.getUserAbusiveComments(siteId,emailId)
  }
}

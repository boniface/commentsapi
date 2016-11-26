package services.comments.Impl

import com.datastax.driver.core.ResultSet
import domain.comments.{Abuse, Comment}
import repositories.comments.{AbuseRepository, CommentRepository}
import services.comments.AbuseService
import services.Service
import scala.concurrent.Future

/**
  * Created by Bonga on 11/12/2016.
  */
class AbuseServiceImpl extends AbuseService with Service{

  override def getAbuseBySubjectId(id: String): Future[Option[Abuse]] = {
    AbuseRepository.getSiteAbuse(id)
  }
  override def save(abuse: Abuse): Future[ResultSet] = {
    val abuseService = Abuse(abuse.subjectId,
      abuse.siteId,
      abuse.commentIdOrResponseId,
      abuse.emailId,
      abuse.details,
      abuse.emailId,
      abuse.date)
    for {
      result <- AbuseRepository.save(abuseService)
    } yield result
  }

  override def getAllAbuse: Future[Seq[Abuse]] = {
    AbuseRepository.getAllAbuse
  }

  override def deleteAll: Future[ResultSet] = {
    AbuseRepository.deleteAll
  }


}

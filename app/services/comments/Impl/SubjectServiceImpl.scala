package services.comments.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.comments.Subject
import repositories.comments.SubjectRepository
import services.Service
import services.comments.SubjectService

import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
class SubjectServiceImpl extends SubjectService with Service{
  override def save(subject: Subject): Future[ResultSet] = {
    SubjectRepository.save(subject)
  }

  override def getSubjectById(siteId: String, subjectId: String): Future[Option[Subject]] = {
    SubjectRepository.getSubjectById(siteId,subjectId)
  }

  override def getSiteSubjects(siteId: String): Future[Seq[Subject]] = {
    SubjectRepository.getSiteSubjects(siteId)
  }
}

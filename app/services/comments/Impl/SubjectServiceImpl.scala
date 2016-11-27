package services.comments.Impl

import com.datastax.driver.core.ResultSet
import domain.comments.{Subject, Comment}
import repositories.comments.{SubjectRepository, CommentRepository}
import services.comments.SubjectService
import services.Service

import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
class SubjectServiceImpl extends SubjectService with Service{


  override def getSubjectBySubjectId(id: String): Future[Option[Subject]] = {
    SubjectRepository.getSiteSubject(id)
  }

  override def save(subject: Subject): Future[ResultSet] = {
    val subjectService = Subject(subject.subjectId,
      subject.siteId,
      subject.name,
      subject.url,
      subject.date)
    for {
      result <- SubjectRepository.save(subjectService)
    } yield result
  }

  override def getAllSubject: Future[Seq[Subject]] = {
    SubjectRepository.getAllSubject
  }

  override def deleteAll: Future[ResultSet] = {
    SubjectRepository.deleteAll
  }


}

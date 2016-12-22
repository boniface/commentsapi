package services.comments

import com.websudos.phantom.dsl.ResultSet
import domain.comments.Subject
import services.comments.Impl.SubjectServiceImpl

import scala.concurrent.Future

/**
  * Created by Bonga on 11/13/2016.
  */
trait SubjectService {

  def save(subject: Subject): Future[ResultSet]

  def getSubjectById(siteId: String, subjectId: String): Future[Option[Subject]]

  def getSiteSubjects(siteId: String): Future[Seq[Subject]]
}

object SubjectService {
  def apply(): SubjectService = new SubjectServiceImpl
}
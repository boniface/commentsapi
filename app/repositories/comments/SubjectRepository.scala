package repositories.comments

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.{KeySpace, RootConnector}
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.comments.Subject

import scala.concurrent.Future

/**
  * Created by Bonga on 10/28/2016.
  */

class SubjectRepository  extends CassandraTable[SubjectRepository, Subject]{

  object siteId extends StringColumn(this) with PartitionKey[String]
  object subjectId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)
  object url extends StringColumn(this)
  object date extends DateTimeColumn(this)

  override def fromRow(r: Row): Subject = {
    Subject(
      siteId(r),
      subjectId(r),
      name(r),
      url(r),
      date(r)
    )
  }
}

object SubjectRepository extends SubjectRepository with RootConnector {

  override lazy val tableName = "subjects"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(subject: Subject): Future[ResultSet] = {
    insert
      .value(_.subjectId, subject.subjectId)
      .value(_. siteId, subject.siteId)
      .value(_. name, subject.name)
      .value(_.url, subject.url)
      .value(_.date, subject.date)
      .future()
  }

  def getSubjectById(siteId: String, subjectId: String): Future[Option[Subject]] = {
    select
      .where(_.siteId eqs siteId)
      .and(_.subjectId eqs subjectId)
      .one()
  }

  def getSiteSubjects(siteId: String): Future[Seq[Subject]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }
}

package repositories.comments

import conf.connection.DataConnection
import domain.comments.{Subject, Abuse, Comment}
import domain.users.User
import io.netty.util.concurrent.Future
import org.h2.engine.Session
import org.h2.result.Row
import views.html.helper.select

/**
  * Created by Bonga on 10/28/2016.
  */

class SubjectRepository  extends CassandraTable[SubjectRepository, Subject]{


  object siteId extends StringColumn(this) with PartitionKey[String]
  object subjectId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)
  object url extends StringColumn(this)
  object date extends StringColumn(this)

  override def fromRow(r: Row): Subject = {
    Subject(
      siteId(r),
      subjectId(r),
      name(r),
      url(r),
      date(r),

    )
  }
}

object SubjectRepository extends SubjectRepository with RootConnector {

  override lazy val tableName = "subject"

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

  def getSubjectBySubjectId(siteId: String, subjectId: String): Future[Option[Abuse]] = {
    select.where(_.siteId eqs siteId).and(_.subjectId eqs subjectId).one()
  }

  def getSiteSubject(siteId: String): Future[Seq[User]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }

}

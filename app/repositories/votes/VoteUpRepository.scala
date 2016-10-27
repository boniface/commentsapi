package repositories.votes


import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.votes.VoteUp
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/**
  * commentIdOrResponseId:String,
  * emailId:String,
  * ipaddress:String,
  * count:Int
  */
class VoteUpRepository extends CassandraTable[VoteUpRepository, VoteUp] {

  object commentIdOrResponseId extends StringColumn(this) with PartitionKey[String]

  object emailId extends StringColumn(this) with PrimaryKey[String]

  object ipaddress extends StringColumn(this)

  object count extends IntColumn(this)


  override def fromRow(r: Row): VoteUp = {
    VoteUp(
      commentIdOrResponseId(r),
      emailId(r),
      ipaddress(r),
      count(r)
    )
  }
}

object VoteUpRepository extends VoteUpRepository with RootConnector {

  override lazy val tableName = "voteUp"

  override implicit def space: KeySpace = DataConnection.keySpace
  override implicit def session: Session = DataConnection.session

    def save(voteUp: VoteUp): Future[ResultSet] = {
      insert
        .value(_.commentIdOrResponseId, voteUp.commentIdOrResponseId)
        .value(_.emailId, voteUp.emailId)
        .value(_.ipaddress, voteUp.ipaddress)
        .value(_.count, voteUp.count)
        .future()
    }


  def getUserByEmail(commentIdOrResponseId: String, emailId: String): Future[Option[VoteUp]] = {
    select.where(_.commentIdOrResponseId eqs commentIdOrResponseId).and(_.emailId eqs emailId).one()
  }

  def getSiteUsers(commentIdOrResponseId: String): Future[Seq[VoteUp]] = {
    select.where(_.commentIdOrResponseId eqs commentIdOrResponseId).fetchEnumerator() run Iteratee.collect()
  }

}

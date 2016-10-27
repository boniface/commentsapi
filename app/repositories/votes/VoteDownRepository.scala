package repositories.votes

import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.votes.VoteDown
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
class VoteDownRepository extends CassandraTable[VoteDownRepository, VoteDown] {

  object commentIdOrResponseId extends StringColumn(this) with PartitionKey[String]

  object emailId extends StringColumn(this) with PrimaryKey[String]

  object ipaddress extends StringColumn(this)

  object count extends IntColumn(this)


  override def fromRow(r: Row): VoteDown = {
    VoteDown(
      commentIdOrResponseId(r),
      emailId(r),
      ipaddress(r),
      count(r)
    )
  }
}

object VoteDownRepository extends VoteDownRepository with RootConnector {

  override lazy val tableName = "voteDown"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(voteDown: VoteDown): Future[ResultSet] = {
    insert
      .value(_.commentIdOrResponseId, voteDown.commentIdOrResponseId)
      .value(_.emailId, voteDown.emailId)
      .value(_.ipaddress, voteDown.ipaddress)
      .value(_.count, voteDown.count)
      .future()
  }

  def getUserByEmail(commentIdOrResponseId: String, emailId: String): Future[Option[VoteDown]] = {
    select.where(_.commentIdOrResponseId eqs commentIdOrResponseId).and(_.emailId eqs emailId).one()
  }

  def getSiteUsers(siteId: String): Future[Seq[VoteDown]] = {
    select.where(_.commentIdOrResponseId eqs commentIdOrResponseId).fetchEnumerator() run Iteratee.collect()
  }
}
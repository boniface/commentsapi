package repositories.votes

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.votes.VoterRegister
import scala.concurrent.Future


/**
  * Created by fatimam on 12/11/2016.
  */

sealed class VoterRegisterRepository extends CassandraTable[VoterRegisterRepository, VoterRegister] {

  object commentIdOrResponseId extends StringColumn(this) with PartitionKey[String]
  object emailId extends StringColumn(this)
  object ipaddress extends StringColumn(this)


  override def fromRow(row: Row): VoterRegister = {
    VoterRegister(
      commentIdOrResponseId(row),
      emailId(row),
      ipaddress(row)
    )
  }
}

object VoterRegisterRepository extends VoterRegisterRepository with RootConnector {
  override lazy val tableName = "voterregisterkeys"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(voterregister: VoterRegister): Future[ResultSet] = {
    insert
      .value(_.commentIdOrResponseId, voterregister.commentIdOrResponseId)
      .value(_.emailId, voterregister.emailId)
      .value(_.ipaddress, voterregister.ipaddress)
      .future()
  }
  def getVoterRegisterById(commentIdOrResponseId: String): Future[Option[VoterRegister]] = {
    select.where(_.commentIdOrResponseId eqs commentIdOrResponseId).one()
  }
  def getAllkeys: Future[Seq[VoterRegister]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

}

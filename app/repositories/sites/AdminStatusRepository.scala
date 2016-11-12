package repositories.sites

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PrimaryKey
import conf.connection.DataConnection
import domain.sites.AdminStatus
import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/24.
  */
class AdminStatusRepository extends CassandraTable[AdminStatusRepository,AdminStatus]{

  object adminStatusId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)

  override def fromRow(r:Row):AdminStatus ={
    AdminStatus(adminStatusId(r),name(r))
  }
}
object AdminStatusRepository extends AdminStatusRepository with RootConnector{
  override lazy val tableName = "adminStatus"
  override implicit def space: KeySpace = DataConnection.keySpace
  override implicit def session: Session = DataConnection.session

  def save(adminStatus: AdminStatus):Future[ResultSet] = {
    insert
      .value(_.adminStatusId,adminStatus.adminStatusId)
      .value(_.name,adminStatus.name)
      .future()
  }

  def getAdminStatusById(adminStatusId:String):Future[Option[AdminStatus]] ={
    select.where(_.adminStatusId eqs adminStatusId).one()
  }

  def deleteById(adminStatusId: String):Future[ResultSet] = {
    delete.where(_.adminStatusId eqs adminStatusId).future()
  }

  def getAllAdminStatus: Future[Seq[AdminStatus]] = {
    select.all().fetch()
  }
}
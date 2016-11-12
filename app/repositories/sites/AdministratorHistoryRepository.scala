package repositories.sites


import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.connectors.RootConnector
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.sites.AdministratorHistory

import scala.concurrent.Future

/**
  * Created by Quest on 2016/10/24.
  */
class AdministratorHistoryRepository extends CassandraTable[AdministratorHistoryRepository,AdministratorHistory]{

  object siteId extends StringColumn(this)
  object emailId extends StringColumn(this)
  object adminStatusId extends StringColumn(this)with PrimaryKey[String]
  object onDate extends DateTimeColumn(this) with PartitionKey[DateTime]

  override def fromRow(r:Row): AdministratorHistory = {
    AdministratorHistory(siteId(r),emailId(r),adminStatusId(r),onDate(r))
  }
}

object AdministratorHistoryRepository extends AdministratorHistoryRepository with RootConnector{

  override lazy val tableName = "AdministratorHistory"
  override implicit def space: KeySpace = DataConnection.keySpace
  override implicit def session: Session = DataConnection.session

  def save(administratorHistory: AdministratorHistory):Future[ResultSet]={
    insert
      .value(_.siteId,administratorHistory.siteId)
      .value(_.emailId,administratorHistory.emailId)
      .value(_.adminStatusId,administratorHistory.adminStatusId)
      .value(_.onDate,administratorHistory.date)
      .future()
  }

  def getAdministratorHistoryByDate(ondate:DateTime): Future[Option[AdministratorHistory]]= {
    select.where(_.onDate eqs  ondate ).one()
  }

  def getAllAdministratorsHistory: Future[Seq[AdministratorHistory]]={
    select.all().fetch()
  }

  def deleteAdministratorHistoryById(adminStatusId:String): Future[ResultSet] = {
    delete.where(_.adminStatusId eqs adminStatusId).future()
  }


  def deleteAll:Future[ResultSet]={
    delete.future()
  }
}


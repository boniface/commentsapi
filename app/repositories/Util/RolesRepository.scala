package repositories.Util
import java.sql.ResultSet

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{Session, _}
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.Roles
import org.h2.engine.Session
import play.api.libs.iteratee.Iteratee
import views.html.helper.select

import scala.concurrent.Future

/**
  * Created by fatimam on 27/10/2016.
  */
class RolesRepository extends CassandraTable[RolesRepository, Roles] {

  object id extends StringColumn(this) with PrimaryKey[String]

  object rolename extends StringColumn(this)


  override def fromRow(r: Row): Roles = {
    Roles(
      id(r),
      rolename(r)
    )
  }
}

object RolesRepository extends RolesRepository with RootConnector {

  override lazy val tableName = "roles"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(roles: Roles): Future[ResultSet] = {
    insert
      .value(_.id, roles.id)
      .value(_.rolename, roles.rolename)
      .future()
  }

  def getRoleById(id: String): Future[Option[Roles]] = {
    select.where(_.id eqs id).one()
  }

  def getAllRoles: Future[Seq[Roles]] = {
    select.where(_.id).fetchEnumerator() run Iteratee.collect()
  }

}
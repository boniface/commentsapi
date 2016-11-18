package repositories.users

import domain.users.User
import com.datastax.driver.core.ResultSet
import com.websudos.phantom.builder.{Chainned, Unspecified, Unordered, Unlimited}
import com.websudos.phantom.builder.query.SelectQuery
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.{PrimaryKey}
import com.websudos.phantom.{CassandraTable}
import com.websudos.phantom.connectors.RootConnector
import conf.connection.DataConnection._
import play.api.libs.iteratee.Iteratee
import shapeless.HNil
import scala.concurrent.Future
/**
 * Created by Rosie on 2016/11/12.
 */
class UserRepository extends CassandraTable[UserRepository, User]{

  object siteId extends StringColumn(this) with PrimaryKey[String]
  object email extends StringColumn(this)
  object screenName extends StringColumn(this)
  object firstname extends OptionalStringColumn(this)
  object lastName extends OptionalStringColumn(this)
  object password extends StringColumn(this)

  override def fromRow(r:Row): User = {

    User(siteId(r),email(r),screenName(r),firstname(r),lastName(r),password(r))
  }
}

object UserRepository extends UserRepository with RootConnector {

  override lazy val tableName = "user"

  override implicit def space: KeySpace = keySpace

  override implicit def session: Session = session

  def save(user: User): Future[ResultSet] = {
    insert
      .value(_.siteId, user.siteId)
      .value(_.email, user.email)
      .value(_.screenName, user.screenName)
      .value(_.firstname,user.firstname)
      .value(_.lastName, user.lastName)
      .value(_.password, user.password)
      .future()
  }

  def getUserScreenNameBySiteId(siteId: String): Future[Option[User]] = {
    select.where(_.siteId eqs siteId).one()
  }

  def getUserByEmail(siteId: String,email:String): Future[Option[User]] = {
    //select.where(_.siteId eqs siteId).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
    select.where(_.siteId eqs siteId).one()

  }

}

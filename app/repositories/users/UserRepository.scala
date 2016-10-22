package repositories.users

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.users.User

import scala.concurrent.Future

/**
  * Created siteId:String,
  * email: String,
  * screenName: String,
  * firstname:Option[String],
  * lastName:Option[String],
  * password: String
  */
class UserRepository extends CassandraTable[UserRepository, User] {

  object siteId extends StringColumn(this) with PartitionKey[String]

  object email extends StringColumn(this) with PrimaryKey[String]

  object screenName extends StringColumn(this)

  object firstname extends OptionalStringColumn(this)

  object lastname extends OptionalStringColumn(this)

  object password extends StringColumn(this)

  override def fromRow(r: Row): User = {
    User(
      siteId(r),
      email(r),
      screenName(r),
      firstname(r),
      lastname(r),
      password(r)
    )
  }
}

object UserRepository extends UserRepository with RootConnector {

  override lazy val tableName = "users"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(user: User): Future[ResultSet] = {
    insert
      .value(_.email, user.email)
      .value(_.firstname, user.firstname)
      .value(_.lastname, user.lastName)
      .value(_.password, user.password)
      .value(_.siteId, user.siteId)
      .value(_.screenName, user.screenName)
      .future()
  }

  def getUserByEmail(siteId: String, email: String): Future[Option[User]] = {
    select.where(_.siteId eqs siteId).and(_.email eqs email).one()
  }

  def getSiteUsers(siteId: String): Future[Seq[User]] = {
    select.where(_.siteId eqs siteId).fetchEnumerator() run Iteratee.collect()
  }
}

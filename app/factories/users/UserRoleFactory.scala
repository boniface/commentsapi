package factories.users

import domain.users.UserRole
import org.joda.time.DateTime

/**
 * Created by Rosie on 2016/09/29.
 */
object UserRoleFactory {
  def getUserRole(values:Map[String,String], date:DateTime):UserRole={
    UserRole(emailId = values("emailId"),roleId = values("roleId"),date=date)
  }

}

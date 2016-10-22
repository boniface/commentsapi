package factories.users

import domain.users.UserRole

/**
 * Created by Rosie on 2016/09/29.
 */
object UserRoleFactory {
  def getUserRole(values:Map[String,String]):UserRole={
    UserRole(emailId = values("emailId"),roleId = values("roleId"))
  }

}

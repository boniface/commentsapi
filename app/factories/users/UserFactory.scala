package factories.users

import domain.users.User
/**
 * Created by Rosie on 2016/09/29.
 */
object UserFactory {

  def getUser(values:Map[String,String]):User ={
    User (emailId = values("emailId"),
      screenName = values("screenName"),
      password =values("password"),
      userStatus = values("userStatus"),None )
  }

}

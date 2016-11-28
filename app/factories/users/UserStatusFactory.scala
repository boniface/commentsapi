package factories.users

import domain.users.UserStatus
import org.joda.time.DateTime

/**
 * Created by Rosie on 2016/11/27.
 */
object UserStatusFactory {
  def getUserStatus(values:Map[String,String], date:DateTime):UserStatus ={
    UserStatus (siteId = values("siteId"),userId = values("userStatusId"),status = values("status"),
     date =date)
  }
}

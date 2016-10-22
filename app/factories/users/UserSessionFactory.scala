package factories.users

import domain.users.UserSession
import org.joda.time.DateTime

/**
 * Created by Rosie on 2016/09/29.
 */
object UserSessionFactory {
  def getUserSession(values:Map[String,String], date:DateTime):UserSession ={
    UserSession (email = values("email"),userSessionId = values("userSessionId"),
      startTime =date,ipaddress = values("ipaddress"),browserSession = values("browserSession"),status = values("status"),tokenId = values("tokenId") )
  }

}

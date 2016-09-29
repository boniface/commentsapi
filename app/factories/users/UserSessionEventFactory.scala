package factories.users

import domain.users.UserSessionEvent
import org.joda.time.DateTime

/**
 * Created by Rosie on 2016/09/29.
 */
object UserSessionEventFactory {
  def getUserSessionEvent(values:Map[String,String], date:DateTime):UserSessionEvent ={
    UserSessionEvent (sessionId = values("sessionId"),id = values("id"),eventTime =date,eventName = values("eventName") )
  }

}

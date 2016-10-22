package factories.users

import domain.users.UserLogActivities
import org.joda.time.DateTime

/**
 * Created by Rosie on 2016/09/29.
 */
object UserLogActivitiesFactory {

  def getUserActivity(values:Map[String,String], date:DateTime):UserLogActivities ={
    UserLogActivities (emailId = values("emailId"),id = values("id"),sessionId =values("sessionId"),details = values("details"),date=date,description = values("description") )
  }

}

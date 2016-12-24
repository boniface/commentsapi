package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json


/**
  * Created by hashcode on 2016/10/19.
  */
case class UserStatus(siteId:String,
                      userId:String,
                      status:String,
                      date: DateTime)
object UserStatus{
  implicit val userFmt = Json.format[UserStatus]

}

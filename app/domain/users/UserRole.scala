package domain.users


import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class UserRole( siteId:String,
                     emailId: String,
                     roleId: String,
                     date:DateTime
                   )
object UserRole {
  implicit val userroleFmt = Json.format[UserRole]
}

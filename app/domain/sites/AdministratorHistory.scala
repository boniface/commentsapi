package domain.sites

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/21.
  */
case class AdministratorHistory(siteId:String,
                                emailId:String,
                                adminStatusId:String,
                                date:DateTime)
object AdministratorHistory{
implicit val administratorHistoryFmt = Json.format[AdministratorHistory]
}

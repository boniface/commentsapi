package domain.comments

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/21.
  */
case class Abuse(siteId:String,
                 commentIdOrResponseId:String,
                 date:DateTime,
                 details:String,
                 emailId:String)
object Abuse{
  implicit val abuseFmt =Json.format[Abuse]

}

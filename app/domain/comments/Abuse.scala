package domain.comments

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/21.
  */
case class Abuse(siteId:String,
                 subjectId:String,
                 abuseId:String,
                 details:String,
                 commentIdOrResponseId:String,
                 emailId:String,
                 date:DateTime)
object Abuse{
  implicit val abuseFmt =Json.format[Abuse]

}

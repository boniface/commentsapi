package domain.comments

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/21.
  */
case class Subject(siteId:String,
                   subjectId:String,
                   name:String,
                   url:String,
                   date:DateTime)

object Subject{
  implicit val subjectFmt = Json.format[Subject]

}

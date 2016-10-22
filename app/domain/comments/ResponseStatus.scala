package domain.comments

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/19.
  */
case class ResponseStatus(responseId:String,
                          status:String,
                          date:DateTime)
object ResponseStatus{
implicit val responseStatusId =Json.format[ResponseStatus]
}

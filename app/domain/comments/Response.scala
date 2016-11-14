package domain.comments
import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/20.
  */
case class Response(commentId:String,
                    responseId:String,
                    response:String,
                    emailId:String,
                    ipaddress:String,
                    date:DateTime)

object Response{
  implicit val response =Json.format[Response]
}

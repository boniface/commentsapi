package domain.comments

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/19.
  */
case class CommentStatus(commentId:String,
                         status:String,
                         date:DateTime
                        )
object CommentStatus{
  implicit val commentStatus = Json.format[CommentStatus]

}

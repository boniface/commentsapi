package factories.comments

import domain.comments.{ResponseStatus, CommentStatus}
import org.joda.time.DateTime

/**
  * Created by Bonga on 10/27/2016.
  */
object ResponseStatusFactory {


  def getResponseStatus (value:Map[String,String],dates:DateTime):ResponseStatus = {
    ResponseStatus(responseId = value("responseId"),status = value("status"),date = dates)
  }
}

package factories.comments
import domain.comments.{Response, Comment}
import org.joda.time.DateTime

/**
  * Created by Bonga on 9/27/2016.
  */

object ResponseFactory {

  def getResponse(value:Map[String,String],dates:DateTime):Response = {
    Response(commentId = value("commentId"),responseId = value("responseId"),
      response = value("response"),emailId = value("emailId"),
      ipaddress = value("ipaddress"),date = dates)
  }

}

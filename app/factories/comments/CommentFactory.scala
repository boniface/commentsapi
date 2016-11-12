package factories.comments

import domain.comments.Comment
import org.joda.time.DateTime

/**
  * Created by Bonga on 10/27/2016.
  */
object CommentFactory {


  def getComment (value:Map[String,String],dates:DateTime): Comment ={
    Comment(siteId = value("siteId"),subjectId = value("subjectId"),
      commentId = value("commentId"),emailId = value("emailId"),
      ipaddress = value("ipaddress"),comment = value("comment"),date = dates)

  }
}

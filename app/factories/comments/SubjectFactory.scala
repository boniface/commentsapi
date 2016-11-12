package factories.comments

import domain.comments.Subject
import org.joda.time.DateTime

/**
  * Created by hashcode on 2016/09/21.
  */
object SubjectFactory {

  def createSubject(value:Map[String,String],date:DateTime): Subject ={
    Subject(siteId = value("siteId"),subjectId = value("subjectId"),name = value("name"),url = value("url"),date)
  }



}

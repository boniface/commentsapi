package factories.util

import java.util.Date

import domain.util.Mail

/**
  * Created by Quest on 2016/09/27.
  */
object MailFactory {
  def getMail(values:Map[String,String],dates:Date): Mail ={
    Mail(siteId = values("siteId"),id = values("id"),key = values("key"),
      value = values("value"),host = values("host"),port = values("port"),state = values("state"),date = dates)
  }
}

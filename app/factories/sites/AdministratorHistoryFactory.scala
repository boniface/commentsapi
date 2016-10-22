package factories.sites

import domain.sites.AdministratorHistory
import org.joda.time.DateTime

/**
  * Created by Quest on 2016/09/27.
  */
object AdministratorHistoryFactory {

  def getAdministratorHistory(values:Map[String,String],dates:DateTime): AdministratorHistory ={

    AdministratorHistory(siteId = values("siteId"),emailId = values("emailId"),adminStatusId = values("adminStatusId"),
      date = dates)
  }
}

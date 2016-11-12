package factories.sites

import domain.sites.Administrators

/**
  * Created by Quest on 2016/09/27.
  */
object AdministratorsFactory {
  def getAdministrators(values:Map[String,String]): Administrators ={
    Administrators(siteId = values("siteId"),emailId = values("emailId"))
  }

}

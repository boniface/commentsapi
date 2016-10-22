package factories.sites

import domain.sites.AdminStatus

/**
  * Created by Quest on 2016/09/27.
  */
object AdminStatusFactory {

  def getAdminStatus(values:Map[String,String]): AdminStatus ={

    AdminStatus(adminStatusId = values("adminStatusId"),name = values("name"))

  }

}

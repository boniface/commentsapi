package factories.util

import domain.util.Roles

/**
  * Created by Quest on 2016/09/27.
  */
object RolesFactory {
  def getRoles(values:Map[String,String]): Roles ={
    Roles(id = values("id"),rolename = values("rolename"))
  }
}

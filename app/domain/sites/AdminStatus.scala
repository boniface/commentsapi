package domain.sites

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/21.
  */
case class AdminStatus(
                        adminStatusId:String,
                        name:String)
object AdminStatus{
implicit val administatusFmt =Json.format[AdminStatus]
}

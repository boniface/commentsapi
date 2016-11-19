package domain.users

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class User( siteId:String,
                 email: String,
                 screenName: String,
                 firstname:Option[String],
                 lastName:Option[String],
                 password: String
               )

object User {
  implicit val userFmt = Json.format[User]
}

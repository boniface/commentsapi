package domain.sites

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/21.
  */
case class Site(siteId: String,
                name: String,
                url: String)

object Site {
  implicit val siteFmt = Json.format[Site]
}

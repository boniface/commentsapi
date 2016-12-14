package controllers.util

import javax.inject.Inject

import conf.security.Credential
import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Quest on 2016/12/09.
  */
class TokenRouter @Inject() (token: TokenController) extends SimpleRouter{

  override def routes: Routes = {
    case POST(p"/save")=>
      token.save
    case POST(p"/create")=>
      token.createNewToken(new Credential("email","password"))
    case GET(p"/get/hasTokenExpired")=>
      token.hasTokenExpired("token")
    case GET(p"/get/revokeToken")=>
      token.revokeToken("token")
    case GET(p"/get/validToken")=>
      token.isTokenValid("token","password")
  }
}

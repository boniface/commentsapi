package controllers.util

import javax.inject.Inject

import conf.security.Credential
import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Quest on 2016/12/15.
  */
class UtilRouter @Inject()
(keys:KeysController)
(mail:MailController)
(role: RolesController)
(token: TokenController)
extends SimpleRouter{

  override def routes: Routes = {

    //----Keys routes-----//
    case POST(p"/key/create") =>
      keys.createOrUpdate
    case GET(p"/get/key/all") =>
      keys.getAllKeys
    case GET(p"/get/$id") =>
      keys.getKey(id)
    case GET(p"/revoke/$id") =>
      keys.revokeKey(id)

    //-------mail-------//
    case POST(p"/mail/create") =>
      mail.createOrUpdate
    case GET(p"/get/mail/$orgId")=>
      mail.getMailer(orgId)
    case GET(p"/get/mail/all/$orgId")=>
      mail.getAllMail(orgId)
    case GET(p"/get/mail/$orgId$id")=>
      mail.getMail(orgId,id)

    //------roles-------//
    case POST(p"/role/create")=>
      role.create
    case GET(p"/get/role/$id")=>
      role.getRole(id)
    case GET(p"/get/role/all")=>
      role.getAllRoles

    //-------tokens-----//
    case POST(p"/token/save")=>
      token.save
    case POST(p"/token/create")=>
      token.createNewToken(new Credential("email","password"))
    case GET(p"/get/token/hasTokenExpired/$tokens")=>
      token.hasTokenExpired(tokens)
    case GET(p"/get/token/revokeToken/$tokens")=>
      token.revokeToken(tokens)
    case GET(p"/get/token/validToken/$tokens$password")=>
      token.isTokenValid(tokens,password)
  }

}

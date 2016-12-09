package controllers.util

import javax.inject.Inject

import play.api.routing.Router._
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Quest on 2016/12/07.
  */
class MailRouter @Inject()(mail:MailController)extends SimpleRouter{

  override def routes: Routes ={
    case POST(p"/create") =>
      mail.createOrUpdate
    case GET(p"/get/orgId")=>
      mail.getMailer("orgId")
    case GET(p"/get/all/orgId")=>
      mail.getAllMail("orgId")
    case GET(p"/get/mail")=>
      mail.getMail("orgId","id")
  }
}

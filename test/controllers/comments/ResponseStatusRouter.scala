package controllers.comments
import play.api.routing.Router._
import play.api.routing.sird._
import javax.inject.Inject
import play.api.routing.SimpleRouter


/**
  * Created by Bonga on 12/2/2016.
  */
class ResponseStatusRouter @Inject() (responseStatus: ResponseStatusController) extends SimpleRouter{


  override def routes: Routes = {
    case GET(p"/get/responseStatus") =>
      responseStatus.getResponseStatus(subjectId="100")
    case GET(p"/get/all") =>
      responseStatus.getAllResponseStatus
    case POST(p"/create") =>
      responseStatus.createOrUpdate
  }

}

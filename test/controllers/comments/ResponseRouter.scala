package controllers.comments
import play.api.routing.Router._
import play.api.routing.sird._
import javax.inject.Inject
import play.api.routing.SimpleRouter
/**
  * Created by Bonga on 12/2/2016.
  */
class ResponseRouter @Inject() (response: ResponseController) extends SimpleRouter {


  override def routes: Routes = {
    case GET(p"/get/response") =>
      response.getResponse(subjectId="100")
    case GET(p"/get/all") =>
      response.getAllResponse
    case POST(p"/create") =>
      response.createOrUpdate
  }

}

package controllers.comments
import play.api.routing.Router._
import play.api.routing.sird._
import javax.inject.Inject
import play.api.routing.SimpleRouter

/**
  * Created by Bonga on 12/2/2016.
  */
class CommentStatusRouter  @Inject() (commentStatus: CommentStatusController) extends SimpleRouter{


  override def routes: Routes = {
    case GET(p"/get/commentStatus") =>
      commentStatus.getCommentStatus(subjectId="100")
    case POST(p"/create") =>
      commentStatus.createOrUpdate
  }
}

package controllers.comments
import play.api.routing.Router._
import play.api.routing.sird._
import javax.inject.Inject
import play.api.routing.SimpleRouter

class CommentRouter  @Inject() (comment: CommentController) extends SimpleRouter {

  override def routes: Routes = {
    case GET(p"/get/comment") =>
      comment.getComment(subjectId = "200")
    case GET(p"/get/all") =>
      comment.getAllComment
    case POST(p"/create") =>
      comment.createOrUpdate
  }
}
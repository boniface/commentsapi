package controllers.comments
import javax.inject.Inject
import controllers.sites.{AdministratorsController, SitesController}
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._
/**
  * Created by Bonga on 12/15/2016.
  */
class CommentRouter@Inject()
(abuseController: AbuseController)
(commentController: CommentController)
(commentStatusController: CommentStatusController)
(responseController: ResponseController)
(responseStatusController: ResponseStatusController)
(subjectController: SubjectController)
  extends SimpleRouter {
  override def routes: Routes = {
    //Abuse
    case GET(p"/get/abuse") =>
      abuseController.getAbuse(subjectId = "100")
    case GET(p"/get/all") =>
      abuseController.getAllAbuse
    case POST(p"/create") =>
      abuseController.createOrUpdate

    // Comment

    case GET(p"/get/comment") =>
      commentController.getComment(subjectId = "200")
    case GET(p"/get/all") =>
      commentController.getAllComment
    case POST(p"/create") =>
      commentController.createOrUpdate


    // Comment ItemStatus

    case GET(p"/get/comment") =>
      commentStatusController.getCommentStatus(subjectId = "200")
    case POST(p"/create") =>
      commentStatusController.createOrUpdate

    // Response

    case GET(p"/get/comment") =>
      responseController.getResponse(subjectId = "200")
    case GET(p"/get/all") =>
      responseController.getAllResponse
    case POST(p"/create") =>
      responseController.createOrUpdate

    // Response ItemStatus

    case GET(p"/get/comment") =>
      responseStatusController.getResponseStatus(subjectId = "200")
    case GET(p"/get/all") =>
      responseStatusController.getAllResponseStatus
    case POST(p"/create") =>
      responseStatusController.createOrUpdate

    //Subject

    case GET(p"/get/subject") =>
      subjectController.getSubject(subjectId = "300")
    case GET(p"/get/all") =>
      subjectController.getAllSubject
    case POST(p"/create") =>
      subjectController.createOrUpdate

  }


}

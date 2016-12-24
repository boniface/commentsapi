package controllers.comments
import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._
/**
  * Created by Bonga on 12/15/2016.
  */
class CommentRouter @Inject()
(abuseController: AbuseController)
(commentController: CommentController)
(responseController: ResponseController)
(subjectController: SubjectController)
  extends SimpleRouter {

  override def routes: Routes = {
    //Abuse
    case GET(p"/abuse/$siteId/$itemId") =>
      abuseController.getItemAbuse(siteId,itemId)
    case GET(p"/abuse/user/$siteId/$emailId") =>
      abuseController.getUserAbusiveComments(siteId,emailId)
    case POST(p"/abuse/create") =>
      abuseController.createOrUpdate

    // Comment

    case GET(p"/comment/$siteId/$subjectId/$commentId") =>
      commentController.getComment(siteId,subjectId,commentId)
    case GET(p"/comment/status/$commentsId") =>
      commentController.getCommentStatus(commentsId)
    case POST(p"/comment/create") =>
      commentController.createOrUpdate
    case POST(p"/comment/site/$siteId") =>
      commentController.getSiteComments(siteId)
    case POST(p"/comment/subject/$siteId/$subjectId") =>
      commentController.getSubjectComments(siteId,subjectId)
    case POST(p"/comment/user/$siteId/$emailId") =>
      commentController.getUserComments(siteId,emailId)
    case POST(p"/comment/status/create") =>
      commentController.saveStatus

    // Response

    case GET(p"/response/$commentId/$responseId") =>
      responseController.getResponse(commentId,responseId)
    case GET(p"/response/status/$commentsId") =>
      responseController.getResponseStatus(commentsId)
    case POST(p"/response/create") =>
      responseController.createOrUpdate
    case POST(p"/response/site/$commentId") =>
      responseController.getCommentResponses(commentId)
    case POST(p"/response/user/$emailId") =>
      responseController.getUserResponses(emailId)
    case POST(p"/response/status/create") =>
      responseController.saveStatus



    //Subject

    case GET(p"/subject/$siteId") =>
      subjectController.getSiteSubjects(siteId)
    case GET(p"/subject/$siteId/$subjectId") =>
      subjectController.getSubjectById(siteId,subjectId)
    case POST(p"/subject/create") =>
      subjectController.createOrUpdate

  }


}

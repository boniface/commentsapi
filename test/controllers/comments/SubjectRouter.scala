package controllers.comments
import play.api.routing.Router._
import play.api.routing.sird._
import javax.inject.Inject
import play.api.routing.SimpleRouter

/**
  * Created by Bonga on 12/2/2016.
  */

class SubjectRouter  @Inject() (subject: SubjectController) extends SimpleRouter{

  override def routes: Routes = {
    case GET(p"/get/subject") =>
      subject.getSubject(subjectId="300")
    case GET(p"/get/all") =>
      subject.getAllSubject
    case POST(p"/create") =>
      subject.createOrUpdate
  }

}


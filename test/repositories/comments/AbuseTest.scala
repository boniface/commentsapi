package repositories.comments
import domain.comments.Abuse
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Bonga on 11/20/2016.
  */
class AbuseTest  extends FeatureSpec with GivenWhenThen{

//  feature("Create abuse") {
//    info("Add an abuse")
//    scenario("Add new abuse ") {
//      Given("Given siteId,subjectId,commentIdOrResponseId,abuseId,details,emailId,date")
//      val siteId = "1"
//      val subjectId = "1"
//      val commentIdOrResponseId = "1"
//      val abuseId = "1"
//      val details = "1"
//      val emailId = "1"
//      val date = DateTime
//
//      Then("Add abuse ")
//      val admin = Abuse(siteId,subjectId,commentIdOrResponseId,abuseId,details,emailId,date)
//      val abuseRepo = AbuseRepository
//      abuseRepo.save(admin)
//      Then("Display All ")
//      val displayAllstatuses = Await.result(abuseRepo.getSiteAbuse(siteId), 2 minutes)
//      displayAllstatuses.foreach(i => println("Abuse=======>", i))
//      val displayIdStatus = Await.result(abuseRepo.getAbuseBySubjectId(siteId,subjectId), 2 minutes)
//      displayIdStatus.foreach(i => println("AdminStatus=======>", i))
//    }
//  }

}

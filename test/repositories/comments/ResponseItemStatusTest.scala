package repositories.comments

import domain.comments.{ResponseStatus, CommentStatus}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Bonga on 11/20/2016.
  */
class ResponseItemStatusTest extends FeatureSpec with GivenWhenThen{


  feature("Create responsestatus") {
    info("Add an responsestatus")
    scenario("Add new responsestatus ") {
      Given("Given responseId,status,date")
      val responseId = "4"
      val status = "4"
      val date = DateTime

      Then("Add commentstatus ")
      val resps = ResponseStatus(responseId,status,date)
      val responsestatusRepo = ResponseStatusRepository
      responsestatusRepo.save(resps)
      Then("Display All ")
      val displayAllstatuses = Await.result(responsestatusRepo.getSiteResponsetId(responseId), 2 minutes)
      displayAllstatuses.foreach(i => println("ResponseStatus=======>", i))
      val displayIdStatus = Await.result(responsestatusRepo.getResponsetByResponseId(responseId), 2 minutes)
      displayIdStatus.foreach(i => println("ResponseStatus=======>", i))
    }
  }

}

package repositories.comments

import domain.comments.{Response, Abuse}
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Bonga on 11/20/2016.
  */
class ResponseTest  extends FeatureSpec with GivenWhenThen {


  feature("Create response") {
    info("Add an response")
    scenario("Add new response ") {
      Given("Given commentId,ResponseId,response,emailId,ipaddress,date")
      val commentId = "5"
      val responseId ="5"
      val response = "ewe"
      val emailId = "5"
      val ipaddress ="5"
      val date = DateTime

      Then("Add response ")
      val res = Response( commentId,responseId,response,emailId,ipaddress,date)
      val responseRepo = ResponseRepository
      responseRepo.save(res)
      Then("Display All ")
      val displayAllstatuses = Await.result(responseRepo.getSiteResponse(commentId), 2 minutes)
      displayAllstatuses.foreach(i => println("Response=======>", i))
      val displayIdStatus = Await.result(responseRepo.getResponseByCommentId(responseId,commentId), 2 minutes)
      displayIdStatus.foreach(i => println("Response=======>", i))
    }
  }

}
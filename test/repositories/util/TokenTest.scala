package repositories.util

import domain.util.Token
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repositories.Util.TokenRepository

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  *
  */
class TokenTest extends FeatureSpec with GivenWhenThen{
  feature("Create Token") {
    info("Admin Add a Token")
    scenario("Admin Add a Token") {
      Given("Given A id,tokenValue")
      val tokenValue ="12982"
      val id="122"
      Then("Add Token ")
      val titletext = Token(id,tokenValue)
      val TokenRepo = TokenRepository
      TokenRepo.save(titletext)
      Then("Display All ")
      val displayTokenId = Await.result(TokenRepo.getTokenById(id), 2 minutes)
      displayTokenId.foreach(i => println("Title=======>",i))
    }
  }
}

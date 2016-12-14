package services.votes
import domain.votes.VoterRegister

import org.scalatest.FunSuite
import scala.concurrent.Await
import scala.concurrent.duration._


/**
  * Created by fatimam on 07/12/2016.
  */
class VoterRegisterServiceTest extends FunSuite
{
  test("testSave"){
    val voterRegister = VoterRegister("1","1","156.326.232")
    val result = Await.result(VoterRegisterService.apply().saveOrUpdate(voterRegister), 2minutes)

    assert(result.isExhausted)
  }

  test("testGetVoterRegisterId"){
    val result = Await.result(VoterRegisterService.apply().get("1"),2 minutes)
    assert(result === "1")
  }


  test("testGetAll") {
    val result = Await.result(VoterRegisterService.apply().getAll, 2.minutes)

    assert(result === "test")
  }


}

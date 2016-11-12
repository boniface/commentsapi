package factories.votes

import domain.votes.VoteUp
import org.scalatest.FunSuite

/**
  * Created by hashcode on 2016/09/21.
  */
class VoteFactory$Test extends FunSuite {

  test("testCreateVoteUp") {
    val values = Map("commentIdOrResponse" -> "up", "emailId" -> "3128", "ipaddress" -> "10.18.8.9")
    val count = 1
    val voteUp = VoteFactory.createVoteUp(values, count)
    assert(voteUp == VoteUp(commentIdOrResponseId = "up", emailId = "3128", ipaddress = "10.18.8.9", count = 1))

  }
}

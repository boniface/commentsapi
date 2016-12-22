package factories.users

import domain.users.Reputation
import org.joda.time.DateTime
import org.scalatest.FunSuite
/**
 * Created by Rosie on 2016/09/29.
 */
class ReputationFactoryTest extends FunSuite{

  test ("getReputation"){
    val emailId = "myemail@gmail.com"
    val date = new DateTime(2016,9,27,6,0,0,0)
    val value = 4

//    val reputation = ReputationFactory.getReputation(emailId,date,value)
//    assert (reputation == Reputation(emailId="myemail@gmail.com",date= new DateTime(2016,9,27,6,0,0,0),4) )
  }

}

package factories.votes

import domain.votes.VoteUp

/**
  * Created by hashcode on 2016/09/21.
  */
object VoteFactory {
  def getVotes(values:Map[String,String],count:Int){}//:VoteUp = {

    //VoteUp(commentIdOrResponseId = values("commentIdOrResponseId"),emailId= values("emailId"),ipaddress = values("ipaddress"),count = values(0))
  //}

  def createVoteDown(values:Map[String,String],count:Int)={

  }

  def createVoterRegister(values:Map[String,String])={

  }
//case class VoteDown(commentIdOrResponseId:String,emailId:String,ipaddress:String,count:Int);
 //case class VoteDown(commentIdOrResponseId:String,emailId:String,ipaddress:String,count:Int);
 // VoteUp(commentIdOrResponseId:String,emailId:String,ipaddress:String,count:Int);


}

package factories.users

import domain.users.Reputation
import org.joda.time.DateTime

/**
 * Created by Rosie on 2016/09/29.
 */
object ReputationFactory {

  def getReputation(values:String, dates:DateTime, number:Int):Reputation={
    Reputation(emailId = values,date= dates,value = number)
  }

}

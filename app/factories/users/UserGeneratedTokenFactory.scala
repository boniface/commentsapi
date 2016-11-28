package factories.users

import domain.users.UserGeneratedToken

/**
 * Created by Rosie on 2016/11/27.
 */
object UserGeneratedTokenFactory {

    def getUserGenToken(values:Map[String,String]):UserGeneratedToken ={
      UserGeneratedToken (token = values("token"),status= values("status"),message=values("message"),siteId = values("siteId"))

    }



}

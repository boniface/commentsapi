package factories.users

import domain.users.UserGeneratedToken
import org.scalatest.FunSuite

/**
 * Created by Rosie on 2016/11/27.
 */
class UserGeneratedTokenFactoryTest extends FunSuite{
  test("getUserGenToken"){
    val values = Map("token"->"1","status"->"available","message"->"newToken","siteId"->"hub1")

    val userGenToken = UserGeneratedTokenFactory.getUserGenToken(values)
    assert (userGenToken == UserGeneratedToken(token="1",status="available",message="newToken",siteId="hub1") )
  }
}

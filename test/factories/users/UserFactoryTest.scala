package factories.users

import domain.users.User
import org.scalatest.FunSuite

/**
 * Created by Rosie on 2016/09/29.
 */
class UserFactoryTest extends FunSuite{
  test("getUser"){
    val values = Map("emailId"->"jemymouk@yahoo.fr","screenName"->"Soulja","password"->"jazz123","userStatus"->"offline")

    val user = UserFactory.getUser(values)

//    assert (user == User(emailId="jemymouk@yahoo.fr",screenName = "Soulja",password ="jazz123",userStatus ="offline") )
  }

}

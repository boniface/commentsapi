package factories.util

import domain.util.Token
import org.scalatest.FunSuite

/**
  * Created by Quest on 2016/09/27.
  */
class TokenFactoryTest extends FunSuite{

  test("getToken"){

    val values = Map("id"->"id","tokenValue"->"tokenid")
    val getToken = TokenFactory.getToken(values)
    assert(getToken == Token(id = "id",tokenValue = "tokenid"))
  }

}

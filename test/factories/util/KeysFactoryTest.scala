package factories.util

import domain.util.Keys
import org.scalatest.FunSuite

/**
  * Created by Quest on 2016/09/27.
  */
class KeysFactoryTest extends FunSuite{

  test("getKeys"){
    val values = Map("id"->"1234","value"->"12345")
    val getKeys = KeysFactory.getKeys(values)
    assert(getKeys == Keys(id = "1234",value = "12345"))
  }
}

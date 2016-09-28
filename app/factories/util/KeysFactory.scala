package factories.util

import domain.util.Keys

/**
  * Created by Quest on 2016/09/27.
  */
object KeysFactory {

  def getKeys(values:Map[String,String]): Keys ={
    Keys(id =values("id"),value = values("value") )
  }

}

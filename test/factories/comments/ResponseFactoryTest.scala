package factories.comments

import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by Bonga on 9/28/2016.
  */
class ResponseFactoryTest   extends FunSuite{

  test("testCreateResponse") {

    val values = Map("commentId" -> "id", "responseId" -> "haha","response" -> "hay", "emailId" -> "com", "url" -> "10.18.8.9")
    val dates = new DateTime

  }
}

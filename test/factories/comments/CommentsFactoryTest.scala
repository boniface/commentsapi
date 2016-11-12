package factories.comments

import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by Bonga on 9/28/2016.
  */
class CommentsFactoryTest  extends FunSuite {

  test("testCreateComment") {

    val values = Map("siteId"->"id","subjectId"->"Terrorism","emailId"->"com","url"->"10.18.8.9","comment"->"ewe")
    val dates = new DateTime

  }
}

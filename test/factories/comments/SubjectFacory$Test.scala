package factories.comments

import domain.comments.Subject
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by hashcode on 2016/09/21.
  */
class SubjectFactory$Test extends FunSuite {

  test("testCreateSubject") {
      val values = Map("siteId"->"id","subjectId"->"Terrorism","name"->"Tebogo","url"->"10.18.8.9")
      val dates = new DateTime
    val getSubject = SubjectFacory.createSubject(values,dates)
    assert(getSubject == Subject(siteId = "id",subjectId = "Terrorism",name = "Tebogo",url = "10.18.8.9",dates))
  }

}

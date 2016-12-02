package factories.sites

import domain.sites.Site
import org.scalatest.FunSuite

/**
  * Created by hashcode on 2016/09/21.
  */
class SiteFactoryTest extends FunSuite {

  test("testGetSite") {

    val values = Map("url"->"www.cput.ac.za", "name"->"CPUT","siteId"->"123")

    val site = SiteFactory.getSite(values)

    assert(site == Site(name="CPUT",url="www.cput.ac.za",siteId="123"))
  }

}

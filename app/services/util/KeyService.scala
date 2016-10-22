package services.util

import com.datastax.driver.core.ResultSet
import domain.util.Keys

import scala.concurrent.Future

/**
  * Created by hashcode on 2015/06/14.
  */
trait KeyService {

  def saveOrUpdate(entity: Keys): Future[ResultSet]

  def get(id: String): Future[Option[Keys]]

  def getAll: Future[Seq[Keys]]

}

package services.util

import com.datastax.driver.core.ResultSet
import domain.util.Keys
import services.util.Impl.KeyServiceImpl

import scala.concurrent.Future


trait KeyService {

  def saveOrUpdate(entity: Keys): Future[ResultSet]

  def get(id: String): Future[Option[Keys]]

  def getAll: Future[Seq[Keys]]

}
object KeyService
{
  def apply(): KeyService = new KeyServiceImpl()
}

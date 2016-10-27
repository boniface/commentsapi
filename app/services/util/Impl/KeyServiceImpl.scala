package services.util.Impl

import com.datastax.driver.core.ResultSet
import domain.util.Keys
import repositories.Util.KeysRepository
import domain.util.Keys
import services.Service
import services.util.KeyService

import scala.concurrent.Future


class KeyServiceImpl extends KeyService with Service{

  def saveOrUpdate(entity: Keys): Future[ResultSet] = {
    KeysRepository.save(entity)
  }
  def get(id:String):Future[Option[Keys]] ={
    KeysRepository.getKeyById(id)
  }

  def getAll(id:String):Future[Seq[Keys]] ={
    KeysRepository.getAllkeys
  }

}

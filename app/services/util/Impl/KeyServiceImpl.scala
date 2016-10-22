package services.util.Impl

import com.datastax.driver.core.ResultSet
import domain.util.Keys
import repositories.util.KeysRepository
import services.Service
import services.util.KeyService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
class KeyServiceImpl extends KeyService with Service{

  def saveOrUpdate(entity: Keys): Future[ResultSet] = {
    KeysRepository.save(entity)
  }
  def get(id:String):Future[Option[Keys]] ={
    KeysRepository.getKeyById(id)
  }

  def getAll:Future[Seq[Keys]] ={
    KeysRepository.getAllkeys
  }

}

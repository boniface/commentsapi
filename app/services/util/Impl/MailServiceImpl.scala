package services.util.Impl

import com.datastax.driver.core.ResultSet
import domain.util.Mail
import repositories.Util.MailRepository

import services.Service
import services.util.MailService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random


class MailServiceImpl extends MailService with Service{
  override def saveOrUpdate(entity: Mail): Future[ResultSet]  = {
    MailRepository.save(entity)
  }

  override def get(siteId: String, id: String): Future[Option[Mail]] = {
    MailRepository.getUserById(siteId, id)
  }

  override def getAll(siteId: String): Future[Seq[Mail]] = {
    MailRepository.getAll
  }

  override def getMailer(siteId: String):Future[Mail] = getAll(siteId) map (
    result => {
      result.toVector( new Random().nextInt(result.size))
    })
}

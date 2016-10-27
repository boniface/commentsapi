package services.util

import com.datastax.driver.core.ResultSet
import domain.util.Mail
import services.util.Impl.MailServiceImpl

import scala.concurrent.Future


trait MailService {
  def saveOrUpdate(entity: Mail): Future[ResultSet]

  def get(siteId: String, id: String): Future[Option[Mail]]

  def getAll(siteId: String): Future[Seq[Mail]]

  def getMailer(siteId: String):Future[Mail]
}

object MailService {
  def apply(): MailService = new MailServiceImpl()
}

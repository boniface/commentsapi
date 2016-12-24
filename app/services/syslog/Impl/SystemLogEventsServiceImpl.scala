package services.syslog.Impl

import com.websudos.phantom.dsl._
import domain.syslog.SystemLogEvents
import repositories.syslog.SystemLogEventsRepository
import services.Service
import services.syslog.SystemLogEventsService

import scala.concurrent.Future

/**
  * Created by Quest on 2016/11/03.
  */
class SystemLogEventsServiceImpl extends SystemLogEventsService with Service {

  override def save(systemLogEvents: SystemLogEvents): Future[ResultSet] = {
    SystemLogEventsRepository.save(systemLogEvents)
  }

  override def getEventById(siteId: String, id: String): Future[Option[SystemLogEvents]] = {
    SystemLogEventsRepository.getEventById(siteId, id)
  }

  override def getSiteLogs(siteId: String): Future[Seq[SystemLogEvents]] = {
    SystemLogEventsRepository.getSiteLogs(siteId)
  }
}

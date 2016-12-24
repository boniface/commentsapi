package services.syslog

import com.websudos.phantom.dsl._
import domain.syslog.SystemLogEvents
import services.syslog.Impl.SystemLogEventsServiceImpl

import scala.concurrent.Future

/**
  * Created by Quest on 2016/11/03.
  */
trait SystemLogEventsService {

  def save(systemLogEvents: SystemLogEvents): Future[ResultSet]

  def getEventById(siteId: String, id: String): Future[Option[SystemLogEvents]]

  def getSiteLogs(siteId: String): Future[Seq[SystemLogEvents]]

}

object SystemLogEventsService {
  def apply():SystemLogEventsService = new SystemLogEventsServiceImpl
}
package services.syslog

import com.websudos.phantom.dsl._
import domain.syslog.SystemLogEvents
import services.Service
import services.syslog.Impl.SystemLogEventsServiceImpl

import scala.concurrent.Future

/**
  * Created by Quest on 2016/11/03.
  */
trait SystemLogEventsService {

  def save(systemLogEvents: SystemLogEvents) : Future[ResultSet]
  def getEventById(id:String): Future[Option[SystemLogEvents]]
  def getEventsByDate(date:DateTime):Future[Option[SystemLogEvents]]
  def getEvents:Future[Seq[SystemLogEvents]]
  def deleteById(id: String):Future[ResultSet]
}

object SystemLogEventsService extends Service{
  def apply():SystemLogEventsService = new SystemLogEventsServiceImpl
}
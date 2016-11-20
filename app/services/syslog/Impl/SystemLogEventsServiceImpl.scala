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
class SystemLogEventsServiceImpl extends SystemLogEventsService with Service{

  override def save(systemLogEvents: SystemLogEvents) : Future[ResultSet] = {
    val systemLogEvent = SystemLogEvents(systemLogEvents.orgCode,
      systemLogEvents.id,
      systemLogEvents.eventName,
      systemLogEvents.eventType,
      systemLogEvents.message,
      systemLogEvents.date)

    for{
      result <- SystemLogEventsRepository.save(systemLogEvents)
    }yield result
  }

  override def getEventById(id:String): Future[Option[SystemLogEvents]] = {
    SystemLogEventsRepository.getEventById(id)
  }

  override def getEventsByDate(date:DateTime):Future[Option[SystemLogEvents]] = {
    SystemLogEventsRepository.getEventsByDate(date)
  }

  override def getEvents:Future[Seq[SystemLogEvents]]= {
    SystemLogEventsRepository.getEvents
  }

  override def deleteById(id: String):Future[ResultSet]= {
    SystemLogEventsRepository.deleteById(id)
  }

}

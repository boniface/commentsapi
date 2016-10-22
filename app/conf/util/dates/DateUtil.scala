package conf.util.dates

import java.util.Date

import org.joda.time.{DateTime, DateTimeConstants}

import scala.util.{Failure, Success, Try}
import scalaz.Value


/**
  * Created by hashcode on 2016/10/19.
  */
object DateUtil {

  val SPAM, PENDING, APPROVED, QUESTION, RESPONSE, SMFEED,FEED, POST, ENABLED, DISABLED, TODAY,YESTERDAY,WEEK,MONTH = Value

  def getDate(date: String): Date = {
    date match{
      case "TODAY" =>  DateTime.now.withTimeAtStartOfDay().toLocalDate.toDate
      case "YESTERDAY"=>  DateTime.now.minusDays(1).toDate
      case "WEEK" =>  DateTime.now.withTimeAtStartOfDay.withDayOfWeek(DateTimeConstants.SUNDAY).minusDays(7).toDate
      case "MONTH" =>  DateTime.now.withTimeAtStartOfDay.dayOfMonth.withMinimumValue.toDate
      case _ => DateTime.now.toDate
    }
  }

  def getDateFromString(date:String):Date ={
    DateTime.parse(date).toDate
  }
  def getIntFromString(value:String):Int ={
    Try(Integer.parseInt(value)) match {
      case Success(ans) => ans
      case Failure(ex) => 0
    }
  }

}

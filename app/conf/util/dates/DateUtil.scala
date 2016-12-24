package conf.util.dates

import java.util.Date

import org.joda.time.{DateTime, DateTimeConstants}

import scala.util.{Failure, Success, Try}



/**
  * Created by hashcode on 2016/10/19.
  */
object DateUtil {

  def  SPAM = "SPAM"
  def PENDING="PENDING"
  def APPROVED="APPROVED"
  def QUESTION="QUESTION"
  def RESPONSE="RESPONSE"
  def SMFEED="SMFEED"
  def FEED="FEED"
  def POST="POST"
  def ENABLED="ENABLED"
  def DISABLED="DISABLE"
  def TODAY="TODAY"
  def YESTERDAY="YESTERDAY"
  def WEEK="WEEK"
  def MONTH="MONTH"

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

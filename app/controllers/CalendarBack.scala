package controllers

import org.joda.time.IllegalInstantException
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.Json
import play.api.mvc.Action

object CalendarBack extends ControllerBase {

  private val cal = fabricator.Calendar()

  case class stringSeq(value: Seq[String])
  
  case class stringList(value: List[String])
  
  case class datesList(dates: Seq[String])
  
  case class datesRangeList(range: List[String])

  implicit def stringSeqWrite = Json.writes[stringSeq]
  
  implicit def stringListWrite = Json.writes[stringList]
  
  implicit def datesListWrite = Json.format[datesList]
  
  implicit def datesRangeListWrite = Json.format[datesRangeList]

  private def getDate(year: Int, month: Int, day: Int, hour: Int, minute: Int, format: String): String =  {
    val finalYear = if (year == 0) cal.year.toInt else year
    val finalMonth = if (month == 0) cal.month.toInt else month
    val finalDay = if (day == 0) cal.day(finalYear, finalMonth).toInt else day
    val finalHour = if (hour == 0) cal.hour12h.toInt else hour
    val finalMinute = if (minute == 0) cal.minute.toInt else minute
    try {
      cal.randomDate.inYear(finalYear).inMonth(finalMonth).inDay(finalDay).inHour(finalHour).inMinute(finalMinute).asString(matchFormat(format))
    } catch {
      case e:IllegalInstantException => getDate(finalYear, finalMonth, finalDay+1, finalHour, finalMinute, format)
      
    }
  }

  def time(twentyFourHours: Boolean, json: Boolean) = Action {
    val time = if (twentyFourHours) cal.time24h else cal.time12h
    if (json) Ok(Json.toJson(Map("time" -> time)))
    else Ok(Json.toJson(time))
  }

  def date(year: Int, month: Int, day: Int, hour: Int, minute: Int, format: String, json: Boolean) = Action {
    try {
    val date = getDate(year, month, day, hour, minute, format)
    if (json) Ok(Json.toJson(Map("date" -> date)))
      else Ok(Json.toJson(date))
    } catch {
      case e: IllegalAccessException => BadRequest(e.getMessage)
    }
  }
  
  def dates(year: Int, month: Int, day: Int, hour: Int, minute: Int, format: String, json: Boolean, amount: Int) = Action {
    if (amount < 1) BadRequest("Amount should be more then 1")
    else if (amount == 1) {
      val result = getDate(year, month, day, hour, minute, format)
      if (json) Ok(Json.toJson(Map("dates" -> result))) else Ok(Json.toJson(result))
    } else {
      val result = Seq.fill(amount)(getDate(year, month, day, hour, minute, format))
      if (json) Ok(Json.toJson(datesList(result))) else Ok(Json.toJson(result))
    }
  }
  
  def relativeDate(startPoint: String, years: Int, months: Int, weeks: Int, days: Int, hours: Int, minutes: Int, format: String,  json: Boolean) = Action {
    var relativeDate = ""
    if (startPoint.equals("")){
      relativeDate = cal.relativeDate.years(years).months(months).weeks(weeks).days(days).hours(hours).minutes(minutes).asString(matchFormat(format))
    }else {
      val date = DateTimeFormat.forPattern(format).parseDateTime(startPoint)
      relativeDate = cal.relativeDate.years(years).months(months).weeks(weeks).days(days).hours(hours).minutes(minutes).asString(matchFormat(format))
    }
    if (json) Ok(Json.toJson(Map("relative_date" -> relativeDate))) else Ok(Json.toJson(relativeDate))
  }




}

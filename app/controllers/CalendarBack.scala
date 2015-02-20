package controllers

import org.joda.time.IllegalInstantException
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.Json
import play.api.mvc.Controller
import play.api.mvc.Action

object CalendarBack extends Controller {

  private val cal = fabricator.Calendar()

  case class stringSeq(value: Seq[String])
  
  case class stringList(value: List[String])

  implicit def stringSeqWrite = Json.writes[stringSeq]
  implicit def stringListWrite = Json.writes[stringList]

  private def getDate(year: Int, month: Int, day: Int, hour: Int, minute: Int, format: String): String =  {
    val finalYear = if (year == 0) cal.year.toInt else year
    val finalMonth = if (month == 0) cal.month.toInt else month
    val finalDay = if (day == 0) cal.day(finalYear, finalMonth).toInt else day
    val finalHour = if (hour == 0) cal.hour.toInt else hour
    val finalMinute = if (minute == 0) cal.minute.toInt else minute
    try {
      cal.date(finalYear, finalMonth, finalDay, finalHour, finalMinute, format)
    } catch {
      case e:IllegalInstantException => getDate(finalYear, finalMonth, finalDay+1, finalHour, finalMinute, format)
      
    }
  }

  def time(twentyFourHours: Boolean, json: Boolean) = Action {
    if (json) Ok(Json.toJson(Map("value" -> cal.time(twentyFourHours))))
    else Ok(Json.toJson(cal.time(twentyFourHours)))
  }

  def date(year: Int, month: Int, day: Int, hour: Int, minute: Int, format: String, json: Boolean) = Action {
    try {
    val date = getDate(year, month, day, hour, minute, format)
    if (json) Ok(Json.toJson(Map("value" -> date)))
      else Ok(Json.toJson(date))
    } catch {
      case e: IllegalAccessException => BadRequest(e.getMessage)
    }
  }
  
  def dates(year: Int, month: Int, day: Int, hour: Int, minute: Int, format: String, json: Boolean, amount: Int) = Action {
    if (amount < 1) BadRequest("Amount should be more then 1")
    else if (amount == 1) {
      val result = getDate(year, month, day, hour, minute, format)
      if (json) Ok(Json.toJson(Map("value" -> result))) else Ok(Json.toJson(result))
    } else {
      val result = Seq.fill(amount)(getDate(year, month, day, hour, minute, format))
      if (json) Ok(Json.toJson(stringSeq(result))) else Ok(Json.toJson(result))
    }
  }
  
  def datesRange(config: String , json: Boolean) = Action {
    val jsonConfig = Json.parse(config)
    val result = cal.datesRange(jsonConfig)
    if (json) Ok(Json.toJson(stringList(result))) else Ok(Json.toJson(result))
  }
  
  def relativeDate(startPoint: String, years: Int, months: Int, weeks: Int, days: Int, hours: Int, minutes: Int, format: String,  json: Boolean) = Action {
    var relativeDate = ""
    if (startPoint.equals("")){
      relativeDate = cal.dateRelative(years, months, weeks, days, hours, minutes, format)
    }else {
      val date = DateTimeFormat.forPattern(format).parseDateTime(startPoint)
      relativeDate = cal.dateRelative(date, years, months, weeks, days, hours, minutes, format)
    }
    if (json) Ok(Json.toJson(Map("value" -> relativeDate))) else Ok(Json.toJson(relativeDate))
  }



}

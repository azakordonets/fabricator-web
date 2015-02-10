package controllers

import play.api.libs.json.Json
import play.api.mvc.Controller
import play.api.mvc.Action

object CalendarBack extends Controller {

  private val cal = fabricator.Calendar()

  case class stringSeq(value: Seq[String])
  
  case class stringList(value: List[String])

  implicit def stringWrite = Json.writes[stringSeq]
  implicit def stringListWrite = Json.writes[stringList]

  private def getDate(year: Int, month: Int, day: Int, hour: Int, minute: Int, format: String): String = (year, month, day, hour, minute) match {
    case (0, 0, 0, 0, 0) => cal.date(format)
    case (year, 0, 0, 0, 0) if year > 0 => {
      val month = cal.month.toInt
      cal.date(year, month.toInt, cal.day(year, month).toInt, cal.hour.toInt, cal.minute.toInt, format)
    }
    case (year, month, 0, 0, 0) if year > 0 && month > 0 =>
      cal.date(year, month.toInt, cal.day(year, month).toInt, cal.hour.toInt, cal.minute.toInt, format)
    case (year, month, day, 0, 0) if year > 0 && month > 0 && day > 0 =>
      cal.date(year, month.toInt, day, cal.hour.toInt, cal.minute.toInt, format)
    case (year, month, day, hour, 0) if year > 0 && month > 0 && day > 0 && hour >= 0 =>
      cal.date(year, month.toInt, day, hour, cal.minute.toInt, format)
    case (year, month, day, hour, minute) if year > 0 && month > 0 && day > 0 && hour >= 0 && minute > 0 =>
      cal.date(year, month.toInt, day, hour, minute, format)
    case _ => throw new IllegalArgumentException("Values should be more then 0")
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
    val jsonConfig = Json.parse(s"""$config""")
    val result = cal.datesRange(jsonConfig)
    if (json) Ok(Json.toJson(stringList(result))) else Ok(Json.toJson(result))
  }



}

package controllers

import play.api.libs.json._
import play.api.mvc._


object AlphaNumericBack extends Controller {


  private val alpha = fabricator.Alphanumeric()
  
  case class integerSeq(integers: Seq[Int])
  case class doubleSeq(doubles: Seq[Double])
  case class stringsSeq(strings: Seq[String])
  case class hashesSeq(hashes: Seq[String])
  case class guidSeq(guids: Seq[String])

  implicit def stringWrite = Json.format[stringsSeq]
  implicit def integerWrite = Json.writes[integerSeq]
  implicit def hashesWrite = Json.format[hashesSeq]
  implicit def doubleWrite = Json.writes[doubleSeq]
  implicit def guidsWrite = Json.format[guidSeq]


  private def generateInt(min: Int, max: Int): Int = (min, max) match {
    case (min, max) if min != 0 && max != 0 && min > max => throw new IllegalArgumentException("Minimal number cannot be more then maximum")
    case (0, 0) => alpha.getInteger
    case (min, max) if min > 0 && max == 0 => alpha.getInteger(min, 1000000000)
    case (min, max) if max > 0 && min == 0 => alpha.getInteger(0, max)
    case (min, max) if min > 0 && max > 0 => alpha.getInteger(min, max)
  }

  private def generateDouble(min: Double, max: Double, accuracy: Int): Double = (min, max, accuracy) match {
    case (min, max, accuracy) if min != 0 && max != 0 && min > max => throw new IllegalArgumentException("Minimal number cannot be more then maximum")
    case (0.0, 0.0, accuracy) if accuracy < 1 => alpha.getDouble
    case (0.0, 0.0, accuracy) if accuracy >= 1 => BigDecimal(alpha.getDouble).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

    case (min, max, accuracy) if min > 0 && max == 0 && accuracy == 1 => alpha.getDouble(min, 1000000000)
    case (min, max, accuracy) if min > 0 && max == 0 && accuracy >= 1 => BigDecimal(alpha.getDouble(min, 1000000000)).
      setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

    case (min, max, accuracy) if max > 0 && min == 0 && accuracy == 1 => alpha.getDouble(0, max)
    case (min, max, accuracy) if max > 0 && min == 0 && accuracy >= 1 => BigDecimal(alpha.getDouble(0, max)).
      setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

    case (min, max, accuracy) if min > 0 && max > 0 && accuracy == 1 => alpha.getDouble(min, max)
    case (min, max, accuracy) if min > 0 && max > 0 && accuracy >= 1 => BigDecimal(alpha.getDouble(min, max)).
      setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }
  
  def integer(min: Int, max: Int, json: Boolean, amount: Int) = Action {
    try {
      if (amount < 1) BadRequest("Amount should be more then 1");
      else if (amount == 1) {
        val generatedResult = generateInt(min, max)
        if (json) Ok(Json.toJson(Map("integer" -> generatedResult))) else Ok(Json.toJson(generatedResult))
      } else {
        val generatedResult = Seq.fill(amount)(generateInt(min, max))
        if (json) Ok(Json.toJson(integerSeq(generatedResult))) else Ok(Json.toJson(generatedResult))
      }
    } catch {
      case e: IllegalArgumentException => BadRequest(e.getMessage)
      case _: Throwable => BadRequest("Bad request")
    }
  }
  
  def integerRange(min: Int, max: Int, step: Int, json: Boolean) = Action {
    try {
      if (step < 1) BadRequest("Step should be more then 1");
      else {
        val generatedResult = alpha.getIntegerRange(min, max, step)
        if (json) Ok(Json.toJson(integerSeq(generatedResult))) else Ok(Json.toJson(generatedResult))
      }
    } catch {
      case e: IllegalArgumentException => BadRequest(e.getMessage)
      case _: Throwable => BadRequest("Bad request")
    }
  }

  def double(min: Double, max: Double, json: Boolean, accuracy: Int, amount: Int) = Action {
    try {
      if (amount < 1) BadRequest("Amount should be more then 1")
      else if (amount == 1) {
        val generatedResult = generateDouble(min, max, accuracy)
        if (json) Ok(Json.toJson(Map("double" -> generatedResult))) else Ok(Json.toJson(generatedResult))
      } else {
        val generatedResult = Seq.fill(amount)(generateDouble(min, max, accuracy))
        if (json) Ok(Json.toJson(doubleSeq(generatedResult))) else Ok(Json.toJson(generatedResult))
      }
    } catch {
      case e: IllegalArgumentException => BadRequest(e.getMessage)
      case _: Throwable => BadRequest("Bad request")
    }
  }

  def doubleRange(min: Double, max: Double, step: Double, json: Boolean) = Action {
    try {
      if (step < 1) BadRequest("Step should be more then 1");
      else {
        val generatedResult = alpha.getDoubleRange(min, max, step)
        if (json) Ok(Json.toJson(doubleSeq(generatedResult))) else Ok(Json.toJson(generatedResult))
      }
    } catch {
      case e: IllegalArgumentException => BadRequest(e.getMessage)
      case _: Throwable => BadRequest("Bad request")
    }
  }


  def string(length: Int, json: Boolean, amount: Int) = Action {
    if (amount < 1) BadRequest("Amount should be more then 1")
    else if (amount == 1) {
      val result = if (length == 30) alpha.getString else alpha.getString(length)
      if (json) Ok(Json.toJson(Map("string" -> result))) else Ok(Json.toJson(result))
    } else {
      val result = Seq.fill(amount)(if (length == 60) alpha.getString else alpha.getString(length))
      if (json) Ok(Json.toJson(Map("string"->result))) else Ok(Json.toJson(result))
    }
  }

  def strings(min: Int, max: Int, amount: Int, json: Boolean) = Action {
    try {
      if (amount < 1) BadRequest("Amount should be more then 1");
      else {
        val generatedResult = alpha.getStrings(min, max, amount)
        if (json) Ok(Json.toJson(stringsSeq(generatedResult))) else Ok(Json.toJson(generatedResult))
      }
    } catch {
      case e: IllegalArgumentException => BadRequest(e.getMessage)
      case _: Throwable => BadRequest("Bad request")
    }
  }

  def hash(length: Int, json: Boolean, amount: Int) = Action {
    if (amount < 1) BadRequest("Amount should be more then 1")
    else if (amount == 1) {
      val result = if (length == 40) alpha.hash else alpha.hash(length)
      if (json) Ok(Json.toJson(Map("hash" -> result))) else Ok(Json.toJson(result))
    } else {
      val result = Seq.fill(amount)(if (length == 40) alpha.hash else alpha.hash(length))
      if (json) Ok(Json.toJson(hashesSeq(result))) else Ok(Json.toJson(result))
    }
  }
  
  def hashesList(min: Int, max: Int, amount: Int, json: Boolean) = Action {
    try {
      if (amount < 1) BadRequest("Amount should be more then 1");
      else {
        val generatedResult = alpha.hashList(min, max, amount)
        if (json) Ok(Json.toJson(hashesSeq(generatedResult))) else Ok(Json.toJson(generatedResult))
      }
    } catch {
      case e: IllegalArgumentException => BadRequest(e.getMessage)
      case _: Throwable => BadRequest("Bad request")
    }
  }
  

  def guid(length: Int, json: Boolean, amount: Int) = Action {
    if (amount < 1) BadRequest("Amount should be more then 1")
    else if (amount == 1) {
      val result = if (length == 5) alpha.guid else alpha.guid(length)
      if (json) Ok(Json.toJson(Map("guid" -> result))) else Ok(Json.toJson(result))
    } else {
      val result = Seq.fill(amount)(if (length == 5) alpha.guid else alpha.guid(length))
      if (json) Ok(Json.toJson(guidSeq(result))) else Ok(Json.toJson(result))
    }
  }

  def letterify(string: String, json: Boolean) = Action {
    if (json) Ok(Json.toJson(Map("value" -> alpha.letterify(string))))
    else Ok(Json.toJson(alpha.letterify(string)))
  }

  def letterifyList(pattern: String, amount: Int, json: Boolean) = Action {
    try {
      if (amount < 1) BadRequest("Amount should be more then 1");
      else {
        val generatedResult = alpha.letterifyList(pattern, amount)
        if (json) Ok(Json.toJson(stringsSeq(generatedResult))) else Ok(Json.toJson(generatedResult))
      }
    } catch {
      case e: IllegalArgumentException => BadRequest(e.getMessage)
      case _: Throwable => BadRequest("Bad request")
    }
  }

  def numerify(string: String, json: Boolean) = Action {
    if (json) Ok(Json.toJson(Map("value" -> alpha.numerify(string))))
    else Ok(Json.toJson(alpha.numerify(string)))
  }

  def numerifyList(pattern: String, amount: Int, json: Boolean) = Action {
    try {
      if (amount < 1) BadRequest("Amount should be more then 1");
      else {
        val generatedResult = alpha.numerifyList(pattern, amount)
        if (json) Ok(Json.toJson(stringsSeq(generatedResult))) else Ok(Json.toJson(generatedResult))
      }
    } catch {
      case e: IllegalArgumentException => BadRequest(e.getMessage)
      case _: Throwable => BadRequest("Bad request")
    }
  }

  def botify(string: String, json: Boolean) = Action {
    if (json) Ok(Json.toJson(Map("value" -> alpha.botify(string))))
    else Ok(Json.toJson(alpha.botify(string)))
  }

  def botifyList(pattern: String, amount: Int, json: Boolean) = Action {
    try {
      if (amount < 1) BadRequest("Amount should be more then 1");
      else {
        val generatedResult = alpha.botifyList(pattern, amount)
        if (json) Ok(Json.toJson(stringsSeq(generatedResult))) else Ok(Json.toJson(generatedResult))
      }
    } catch {
      case e: IllegalArgumentException => BadRequest(e.getMessage)
      case _: Throwable => BadRequest("Bad request")
    }
  }


}


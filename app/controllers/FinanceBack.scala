package controllers

import akka.actor.FSM.->
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object FinanceBack extends Controller{

  private val finance = fabricator.Finance()

  case class stringMapArray(cards: Array[String])
  
  implicit def stringArrayWrite = Json.format[stringMapArray]

  def creditCard(cardType: String ,json: Boolean) = Action {
    val card = cardType match {
      case "master" => finance.mastercreditCard 
      case "visa" => finance.visacreditCard 
      case "americanExpress" => finance.americanExpresscreditCard 
      case "discover" => finance.discoverCreditCard 
      case "diners" => finance.dinersCreditCard 
      case "jcb" => finance.jcbCreditCard 
      case "voyager" => finance.voyagerCreditCard
      case _ => ""
    }
    if (card.equals("")) BadRequest("Incorrect card type is specified. Supported values are : [master, visa, americanExpress, discover, diners, jcb, voyager]")
    if (json) Ok(Json.toJson(Map("card_number" -> card))) else Ok(Json.toJson(card))
  }

  def creditCards(cardType: String , amount: Int, json: Boolean) = Action {
    val cards:Array[String] = cardType match {
      case "master" => finance.mastercreditCards(amount)
      case "visa" => finance.visacreditCards(amount)
      case "americanExpress" => finance.americanExpresscreditCards(amount)
      case "discover" => finance.discoverCreditCards(amount)
      case "diners" => finance.dinersCreditCards(amount)
      case "jcb" => finance.jcbCreditCards(amount)
      case "voyager" => finance.voyagerCreditCards(amount)
      case _ => Array.empty
    }
    if (cards.isEmpty) BadRequest("Incorrect card type is specified. Supported values are : [master, visa, americanExpress, discover, diners, jcb, voyager]")
    if (json) Ok(Json.toJson(stringMapArray(cards))) else Ok(Json.toJson(cards))
  }

}

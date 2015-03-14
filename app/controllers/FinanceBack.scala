package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object FinanceBack extends Controller{

  private val finance = fabricator.Finance()

  case class stringMapArray(cards: Array[String])
  
  implicit def stringArrayWrite = Json.format[stringMapArray]

  def creditCard(cardType: String ,json: Boolean) = Action {
    val card = cardType match {
      case card if card == CreditCard.Master.toString => finance.mastercreditCard
      case card if card == CreditCard.Visa.toString => finance.visacreditCard
      case card if card == CreditCard.AExpress.toString => finance.americanExpresscreditCard
      case card if card == CreditCard.Discover.toString => finance.discoverCreditCard
      case card if card == CreditCard.Diners.toString => finance.dinersCreditCard
      case card if card == CreditCard.Jcb.toString => finance.jcbCreditCard
      case card if card == CreditCard.Voyager.toString => finance.voyagerCreditCard
      case _ => ""
    }
    if (card.equals("")) BadRequest("Incorrect card type is specified. Supported values are : [master, visa, americanExpress, discover, diners, jcb, voyager]")
    if (json) Ok(Json.toJson(Map("card_number" -> card))) else Ok(Json.toJson(card))
  }

  def creditCards(cardType: String , amount: Int, json: Boolean) = Action {
    val cards:Array[String] = cardType match {
      case card if card == CreditCard.Master.toString => finance.mastercreditCards(amount)
      case card if card == CreditCard.Visa.toString => finance.visacreditCards(amount)
      case card if card == CreditCard.AExpress.toString => finance.americanExpresscreditCards(amount)
      case card if card == CreditCard.Discover.toString => finance.discoverCreditCards(amount)
      case card if card == CreditCard.Diners.toString => finance.dinersCreditCards(amount)
      case card if card == CreditCard.Jcb.toString => finance.jcbCreditCards(amount)
      case card if card == CreditCard.Voyager.toString => finance.voyagerCreditCards(amount)
      case _ => Array.empty
    }
    if (cards.isEmpty) BadRequest("Incorrect card type is specified. Supported values are : [master, visa, americanExpress, discover, diners, jcb, voyager]")
    if (json) Ok(Json.toJson(stringMapArray(cards))) else Ok(Json.toJson(cards))
  }

}

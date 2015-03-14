package controllers

object CreditCard extends Enumeration {
  type CreditCard = Value
  val Master = Value("master")
  val Visa = Value("visa")
  val AExpress = Value("americanExpress")
  val Discover = Value("discover")
  val Diners = Value("diners")
  val Jcb = Value("jcb")
  val Voyager = Value("voyager")
}

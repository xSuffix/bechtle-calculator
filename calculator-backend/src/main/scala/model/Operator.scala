package model

protected sealed case class Operator(name: String)

object Operator {
  val LegalOperators: Set[String] = Set("+", "-", "*", "/")
  val + = new Operator("+")
  val - = new Operator("-")
  val * = new Operator("*")
  val / = new Operator("/")

  def apply(input: String): Operator = {
    input match {
      case "+" => Operator.+
      case "-" => Operator.-
      case "*" => Operator.*
      case "/" => Operator./
    }
  }
}

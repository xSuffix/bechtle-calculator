package model

protected sealed case class InternalState(name: String)

object InternalState {
  val Initial = InternalState("Initial")
  val Operand1 = InternalState("Operand 1")
  val Operand1Dec = InternalState("Operand 1 Decimal")
  val Operator = InternalState("Operator")
  val Operand2 = InternalState("Operand 2")
  val Operand2Dec = InternalState("Operand 2 Decimal")
}
package model

case class InternalData(
  state: InternalState = InternalState.Initial,
  operand1: String = "0",
  operator: Operator = Operator.+,
  operand2: String = "0"
)
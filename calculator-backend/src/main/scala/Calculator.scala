import model.InternalData
import model.InternalState
import model.Operator

class Calculator {

    import Calculator._

    private var internalData = InternalData()

    def sendInput(input: String): String = {
      val newInternalData: InternalData = internalData.state match {
        case InternalState.Initial =>
          this handleInitial input

        case InternalState.Operand1 =>
          this handleOperand1 input

        case InternalState.Operand1Dec =>
          this handleOperand1Dec input

        case InternalState.Operator =>
          this handleOperator input

        case InternalState.Operand2 =>
          this handleOperand2 input

        case InternalState.Operand2Dec =>
          this handleOperand2Dec input

        case _ =>
          this.internalData
      }
      this.internalData = newInternalData

      this.createDisplayContent()
    }

    private def createDisplayContent(): String = {
    if (internalData.state == InternalState.Operand2 ||internalData.state == InternalState.Operand2Dec) {
      internalData.operand2
    } else {
      internalData.operand1
    }
  }

  private def handleInitial(input: String): InternalData = {
    input match {
      case Resolve =>
        resolve()
      case Clear =>
        clear()
      case someNumber if LegalNumbers contains someNumber =>
        internalData.copy(
          state = InternalState.Operand1,
          operand1 = someNumber
        )
      case someOperator if Operator.LegalOperators contains someOperator =>
        internalData.copy(
          state = InternalState.Operator,
          operator = Operator(someOperator),
          operand2 = internalData.operand1
        )
      case Comma =>
        internalData.copy(
          state = InternalState.Operand1Dec,
          operand1 = "0,"
        )
      case _ =>
        internalData
    }
  }

  private def handleOperand1(input: String): InternalData = {
    input match {
      case Resolve =>
        resolve()
      case Clear =>
        clear()
      case someNumber if LegalNumbers contains someNumber =>
        internalData.copy(
          operand1 = if (internalData.operand1 == "0") {
            someNumber
          } else {
            internalData.operand1 + someNumber
          }
        )
      case someOperator if Operator.LegalOperators contains someOperator =>
        internalData.copy(
          state = InternalState.Operator,
          operator = Operator(someOperator),
          operand2 = internalData.operand1
        )
      case Comma =>
        internalData.copy(
          state = InternalState.Operand1Dec,
          operand1 = internalData.operand1 + ","
        )
      case _ =>
        internalData
    }
  }

  private def handleOperand1Dec(input: String): InternalData = {
    input match {
      case Resolve =>
        resolve()
      case Clear =>
        clear()
      case someNumber if LegalNumbers contains someNumber =>
        internalData.copy(
          operand1 = internalData.operand1 + someNumber
        )
      case someOperator if Operator.LegalOperators contains someOperator =>
        internalData.copy(
          state = InternalState.Operator,
          operator = Operator(someOperator),
          operand2 = internalData.operand1
        )
      case _ =>
        internalData
    }
  }

  private def handleOperator(input: String): InternalData = {
    input match {
      case Resolve =>
        resolve()
      case Clear =>
        clear()
      case someNumber if LegalNumbers contains someNumber =>
        internalData.copy(
          state = InternalState.Operand2,
          operand2 = someNumber
        )
      case someOperator if Operator.LegalOperators contains someOperator =>
        internalData.copy(
          operator = Operator(someOperator),
          operand2 = internalData.operand1
        )
      case Comma =>
        internalData.copy(
          state = InternalState.Operand2Dec,
          operand1 = "0,"
        )
      case _ =>
        internalData.copy()
    }
  }

  private def handleOperand2(input: String): InternalData = {
    input match {
      case Resolve =>
        resolve()
      case Clear =>
        clear()
      case someNumber if LegalNumbers contains someNumber =>
        internalData.copy(
          operand2 = if (internalData.operand2 == "0") {
            someNumber
          } else {
            internalData.operand2 + someNumber
          }
        )
      case someOperator if Operator.LegalOperators contains someOperator =>
        resolve()
        internalData.copy(
          state = InternalState.Operator,
          operator = Operator(someOperator),
          operand2 = internalData.operand1
        )
      case Comma =>
        internalData.copy(
          state = InternalState.Operand2Dec,
          operand2 = internalData.operand2 + ","
        )
      case _ =>
        internalData
    }
  }

  private def handleOperand2Dec(input: String): InternalData = {
    input match {
      case Resolve =>
        resolve()
      case Clear =>
        clear()
      case someNumber if LegalNumbers contains someNumber =>
        internalData.copy(
          operand2 = internalData.operand2 + someNumber
        )
      case someOperator if Operator.LegalOperators contains someOperator =>
        resolve()
        internalData.copy(
          state = InternalState.Operator,
          operator = Operator(someOperator),
          operand2 = internalData.operand1
        )
      case _ =>
        internalData
    }
  }

  private def calculateResult(internalData: InternalData): InternalData = {
    val op1Double = internalData.operand1.replace(",", ".").toDouble
    val op2Double = internalData.operand2.replace(",", ".").toDouble

    val resultDouble = internalData.operator match {
      case Operator.+ => op1Double + op2Double
      case Operator.- => op1Double - op2Double
      case Operator.* => op1Double * op2Double
      case Operator./ => op1Double / op2Double
    }

    internalData.copy(
      operand1 = if (resultDouble == resultDouble.toInt) {
        resultDouble.toInt.toString
      } else {
        resultDouble.toString.replace(".", ",")
      }
    )
  }

  private def resolve(): InternalData = {
    calculateResult(internalData).copy(
      state = InternalState.Initial
    )
  }

  private def clear(): InternalData = {
    internalData.copy(
      state = InternalState.Initial,
      operand1 = "0",
      operator = Operator.+,
      operand2 = "0"
    )
  }
}

object Calculator {
  val LegalNumbers: IndexedSeq[String] = 0 to 9 map (_.toString)
  val Comma = ","
  val Resolve = "="
  val Clear = "c"

  def apply(): Calculator = new Calculator()
}
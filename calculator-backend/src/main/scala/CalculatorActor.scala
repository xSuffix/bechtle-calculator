import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Props

object CalculatorActor {
  def props(): Props = Props(new CalculatorActor)
}

final class CalculatorActor extends Actor with ActorLogging {

  val calc = Calculator()

  override def preStart(): Unit = log.info("Calculator started")
  override def postStop(): Unit = log.info("Calculator stopped")

  override def receive: Receive = {
    case someParameter: String =>
      val sender = this.sender()
      log.info(s"got input: $someParameter")
      val displayContent = calc sendInput someParameter
      sender ! displayContent

    case allOtherCases =>
      log.info(s"wrong input: $allOtherCases")
  }
}

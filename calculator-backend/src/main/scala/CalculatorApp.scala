import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute
import akka.stream.ActorMaterializer

import scala.io.StdIn
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import spray.json.DefaultJsonProtocol._

case class CalcCommand(command: String)

object Api extends App {
  implicit val system = ActorSystem("calculator-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher


  val calculator = system.actorOf(CalculatorActor.props(), "singleton-calculator")
  implicit val timeout: Timeout = Timeout(5 seconds)

  implicit val calcCommandFormat = DefaultJsonProtocol.jsonFormat1(CalcCommand)

  val route =
    path("api") {
      post {
        entity(as[CalcCommand]) {calcCmd => handlePostRequest(calcCmd)}
      }
  }

  private def handlePostRequest(calcCommand: CalcCommand) = {
    val cmd = calcCommand.command
    val resultFuture = (calculator ? cmd).mapTo[String]
    onComplete(resultFuture) { result =>
      complete(result)
    }
  }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8081)

  println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}

object CalculatorApp extends App {

  override def main(args: Array[String]): Unit = {
    val system = ActorSystem("calculator-system")

    try {
      val calculator = system.actorOf(CalculatorActor.props(), "singleton-calculator")
      implicit val timeout = Timeout(5 seconds)

      while (true) {
        val input = StdIn.readLine()
        val displayContentFuture = (calculator ? input).mapTo[String]

        displayContentFuture map println
      }
    } finally {
      system.terminate()
    }

  }
}
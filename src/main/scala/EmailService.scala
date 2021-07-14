import akka.NotUsed
import akka.actor.typed.ActorSystem

// main
object EmailService extends App {
  //#actor-system
  val serviceStart: ActorSystem[NotUsed] = ActorSystem(ServiceStart(), "start")
  //#actor-system
}

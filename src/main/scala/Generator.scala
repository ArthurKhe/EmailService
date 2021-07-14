import Sender.Letter
import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors

import java.time.LocalDateTime
import scala.util.Random

// actor generator
object Generator {
  final case class Generation(count: Int)

  val rnd = new Random()

  def apply(sender: ActorRef[Letter]): Behavior[Generation] =
    Behaviors.receiveMessage { message =>
      for (i <- 0 until message.count) {
        sender ! Letter(i, i, State(rnd.nextInt(7)), LocalDateTime.now())
      }
      Behaviors.same
    }
}

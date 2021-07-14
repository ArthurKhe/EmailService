import Generator.Generation
import akka.NotUsed
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object ServiceStart {
  def apply(): Behavior[NotUsed] = Behaviors.setup { context =>
    val sender = context.spawn(Sender(), "sender")
    val generator = context.spawn(Generator(sender), "generator")
    generator ! Generation(2)
    Behaviors.empty
  }
}

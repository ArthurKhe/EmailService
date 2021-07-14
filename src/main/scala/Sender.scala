import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import org.apache.commons.mail.{DefaultAuthenticator, SimpleEmail}
import java.time.LocalDateTime
import scala.io.Source

// actor sender
object Sender {
  final case class Letter(id: Int, userId: Int, state: State.Value, timestamp: LocalDateTime){
    override def toString: String = {
      s"Уважаемый пользователь ваша заявка:$id находится в состоянии: $state.\nВремя изменения статуса: $timestamp"
    }
  }

  def downloadData(file: String): Option[Source] = {
    try {
      Some(Source.fromFile(file))
    } catch {
      case _: Exception => None
    }
  }

  def apply(): Behavior[Letter] = Behaviors.receive { (context,message) =>
    context.log.info(message.toString())
    downloadData("./Database/user.csv") match {
      case Some(data) =>
        for (line <- data.getLines) {
          val cols = line.split(",").map(_.trim)
          if (cols(0).toInt == message.userId){
            for (k <- 2 to 9){
              if ((State(k-2) == message.state) && cols(k).toBoolean){
                println("Sending message!!!")
                val email = new SimpleEmail()
                email.setHostName("smtp.googlemail.com")
                email.setSmtpPort(465)
                email.setAuthenticator(new DefaultAuthenticator(raw"username", raw"password"))
                email.setSSLOnConnect(true)
                email.setFrom("username")
                email.setSubject("TestMail")
                email.setMsg(message.toString())
                email.addTo(cols(1))
                email.send()
              }
            }
          }
        }

      case None => println("Not found a file")
    }
    Behaviors.same
  }
}

// enum state
object State extends Enumeration {
  type state = Value
  val application_accepted, data_validation, processing, accepted_for_work, rejected, performed, completed, failed = Value
}
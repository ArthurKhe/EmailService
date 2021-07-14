name := "email_service"

version := "0.1"

scalaVersion := "2.13.6"

val AkkaVersion = "2.6.15"
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "org.apache.commons" % "commons-email" % "1.5"

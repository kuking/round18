scalaVersion := "2.12.4"

enablePlugins(JavaServerAppPackaging)
enablePlugins(DockerPlugin)

organization := "uk.kukino"
name := "round18Console"
version := "0.1"
description := "A web-console using Spring Boot & Scalam integrating Kafka, Spark, Akka, Hadoop and others (TBD)"
organizationHomepage := Some(url("https://kukino.uk"))


libraryDependencies += "org.springframework.boot" % "spring-boot-starter-web" % "2.0.0.RELEASE"
libraryDependencies += "org.springframework.boot" % "spring-boot-configuration-processor" % "2.0.0.RELEASE"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.11.0.2"

libraryDependencies += "org.scalamock" %% "scalamock" % "4.1.0" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

mainClass in Compile := Some("uk.kukino.round18.console.ConsoleApplication")

dockerExposedPorts := Seq(8080)
scalaVersion := "2.12.4"


organization := "uk.kukino"
name := "round18Console"
version := "0.1"
description := "A web-console using Spring Boot & Scalam integrating Kafka, Spark, Akka, Hadoop and others (TBD)"
organizationHomepage := Some(url("https://kukino.uk"))


libraryDependencies += "org.springframework.boot" % "spring-boot-starter-web" % "2.0.0.RELEASE"
libraryDependencies += "org.springframework.boot" % "spring-boot-configuration-processor" % "2.0.0.RELEASE"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"


mainClass in Compile := Some("uk.kukino.round18.console.ConsoleApplication")



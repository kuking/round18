# Round18
WIP, hopefully this ends up being a nice example of a full-fledged micro-services stack made up with: Kafka, Spring Boot components, ci-auto-deployments
It is all in one project as it is meant to be simple proof-of-concept.
Other shinny technologies that might get included: Spark, Akka(TBD)


## Components
- Console
-- Spring Boot, Scala, Web gui
-- it can insert random data into Kafka
- Kafka server
-- Kafka Listener, rewriter
-- Spring Boot, Scala
-- Listen to Kafka streams and re-create another stream with a rolling average
- Kafka Listener, rewriter
-- Python, similar but in Python
- Dockers:
-- Log aggregator (TBD)
-- Jenkins deployer (TBD)
-- Sonar (TBD)


package uk.kukino.round18.console.service

import java.util.Properties
import javax.annotation.PostConstruct

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service

import scala.beans.BeanProperty
import scala.util.Random

@Service
@ConfigurationProperties
class KafkaService {

  val topic = "test" //xxx: configure
  @BeanProperty var kafkaProducerEndpoint : Properties = null
  var kafkaProducer : KafkaProducer[String, String] = null
  val rnd = new Random()

  @PostConstruct
  def createProducer(): Unit = {
    kafkaProducer = new KafkaProducer[String, String](kafkaProducerEndpoint)
  }

  def sendRandomMessages(qty : Int) : Unit = {
    for ( i <- 1 to qty) {
      val data = new ProducerRecord[String, String](topic, s"$i:${rnd.nextInt()}")
      kafkaProducer.send(data)
    }
  }

}

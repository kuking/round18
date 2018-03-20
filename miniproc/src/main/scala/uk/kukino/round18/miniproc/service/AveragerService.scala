package uk.kukino.round18.miniproc.service

import java.util.Properties
import javax.annotation.PostConstruct

import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service

import scala.beans.BeanProperty
import scala.collection.JavaConverters._

@Service
@ConfigurationProperties
class AveragerService extends Runnable {

  private val logger = Logger[AveragerService]

  val topicIn = "test"
  val topicOut = "averaged"
  @BeanProperty var kafkaConsumerEndpoint: Properties = null
  var kafkaConsumer: KafkaConsumer[String, String] = null
  var kafkaProducer: KafkaProducer[String, String] = null

  @PostConstruct
  def setup(): Unit = {
    logger.info("Setting up KafkaConsumer")
    kafkaConsumer = new KafkaConsumer[String, String](kafkaConsumerEndpoint)
    // kafkaProducer = new KafkaProducer[String, String](kafkaEndpoint)
    kafkaConsumer.subscribe(List(topicIn).asJava)

    new Thread(this).start()
  }

  override def run(): Unit = {
    logger.info("Starting consumer thread")
    kafkaConsumer.poll(0) // so partitions are there
    kafkaConsumer.assignment.forEach(tp => {
      logger.info(s"Seeking to beginning for $tp")
      kafkaConsumer.seek(tp, 0)
    })

    var total: Long = 0
    while (true) {
      var records = kafkaConsumer.poll(1000)
      var count = 0
      records.forEach(record => {
        //          logger.info("Received: " + record)
        //          logger.info(record.value())
        count += 1
      })
      if (count > 0) {
        total += count
        logger.info(s"Processed $count records, total so far $total")
      }

    }
  }

}

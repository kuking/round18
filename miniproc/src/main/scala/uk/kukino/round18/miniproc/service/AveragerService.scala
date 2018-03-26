package uk.kukino.round18.miniproc.service

import java.util.Properties
import javax.annotation.PostConstruct

import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service
import uk.kukino.round18.miniproc.utils.{CanAverage, MovingAverage, SimpleAverage}

import scala.beans.BeanProperty
import scala.collection.JavaConverters._

@Service
@ConfigurationProperties
class AveragerService extends Runnable {

  private val logger = Logger[AveragerService]

  val topicIn = "test"
  val topicOut = "averaged"
  @BeanProperty var kafkaConsumerEndpoint: Properties = null
  @BeanProperty var kafkaProducerEndpoint: Properties = null
  var kafkaConsumer: KafkaConsumer[String, String] = null
  var kafkaProducer: KafkaProducer[String, String] = null
  var averages : Array[CanAverage] = null

  @PostConstruct
  def setup(): Unit = {
    logger.info("Setting up KafkaConsumer")
    kafkaConsumer = new KafkaConsumer[String, String](kafkaConsumerEndpoint)
    kafkaProducer = new KafkaProducer[String, String](kafkaProducerEndpoint)
    kafkaConsumer.subscribe(List(topicIn).asJava)

    averages = new Array[CanAverage](5)
    averages(0) = new SimpleAverage
    for (n <- 1 until 5)  averages(n) = new MovingAverage(5*n)

    new Thread(this).start()
  }

  override def run(): Unit = {
    logger.info("Starting consumer thread")

    kafkaConsumer.poll(1000) // XXX: how can we do this more deterministic?
    kafkaConsumer.seekToBeginning(kafkaConsumer.assignment())

    val pattern = "([0-9]+):([-0-9]+)".r

    var total: Long = 0
    while (true) {
      val records = kafkaConsumer.poll(1000)
      var count = 0
      records.forEach(record => {
        val value = record.value()
        value match {
          case pattern(_, random) => {
            averages.foreach( a => a.+=(BigDecimal.apply(random)))
            count += 1
          }
          case _ => logger.warn("Unrecognized format: " + value)
        }
      })
      if (count > 0) {
        total += count
        val stAverages = averages.map( a => f"${a.avg()}%.2f" ).mkString(" ")
        logger.info(s"Processed $count records, total so far $total, averages are: ${stAverages}")
      }
    }

  }

}

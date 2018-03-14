package uk.kukino.round18.console.controller

import com.typesafe.scalalogging.Logger
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import uk.kukino.round18.console.service.KafkaService

import scala.beans.BeanProperty

@RestController
class InsertIntoKafka (kafkaService : KafkaService) {

  private val logger = Logger[InsertIntoKafka]

  @RequestMapping(path = Array("/api/kafka/insert"), method=Array(RequestMethod.POST, RequestMethod.GET))
  def handleInsert(@RequestParam(value = "q", defaultValue="100") quantity: Int): Result = {
    if (0 >= quantity || quantity > 10000) {
      logger.error(s"handleInsert requested with invalid size ($quantity)")
      new Result(false, 0)
    } else {
      kafkaService.sendRandomMessages(quantity)
      logger.info(s"handleInsert successfully inserted ($quantity)")
      new Result(true, quantity)
    }
  }

  class Result(@BeanProperty var success :Boolean, @BeanProperty var inserted: Int)

}

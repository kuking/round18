package uk.kukino.round18.miniproc.utils

import scala.collection.mutable

class MovingAverage(val slots :Int) extends CanAverage {

  private var buffer : mutable.Buffer[BigDecimal] = new mutable.ListBuffer()

  override def +=(value: BigDecimal): Unit = {
    buffer.append(value)
    if (buffer.lengthCompare(slots) > 0) buffer.remove(0)
  }

  override def avg(): BigDecimal = {
    if (buffer.isEmpty) {
      BigDecimal.valueOf(0)
    } else {
      buffer.sum / BigDecimal.valueOf(1)
    }
  }

}

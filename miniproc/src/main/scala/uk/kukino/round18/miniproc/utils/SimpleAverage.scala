package uk.kukino.round18.miniproc.utils

class SimpleAverage extends CanAverage {

  private var total: BigInt = BigInt.apply(0)
  private var sum: BigDecimal = BigDecimal.apply(0)

  def +=(n: BigDecimal): Unit = {
    total += 1
    sum += n
  }

  def avg(): BigDecimal = {
    sum / BigDecimal.apply(total)
  }

}

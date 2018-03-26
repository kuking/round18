package uk.kukino.round18.miniproc.utils

trait CanAverage {

  def += (value : BigDecimal) : Unit
  def avg (): BigDecimal

}

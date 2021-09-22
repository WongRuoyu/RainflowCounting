package com.wong
package RainflowCounting.model

sealed trait  Cycle{ self =>
  def name = "Cycle"

  def start:Reversal

  def end: Reversal

  override def toString() =  s"%s:(%8.3f,%8.3f)".format(name,start.value,end.value)

  override def equals(that: Any): Boolean = that match {
    case half:HalfCycle => half.start.value == self.start.value && half.end.value == self.end.value
    case full:FullCycle => full.start == self.start.value && full.end.value == self.end.value
  }

}

object Cycle{
  def unapply(rfCycle:Cycle):Some[(Reversal,Reversal)] = Some((rfCycle.start,rfCycle.end))
}

case class HalfCycle(start:Reversal,end:Reversal) extends Cycle{
  override def name = "HalfCycle"
}

case class FullCycle(start:Reversal,end:Reversal) extends Cycle{
  override def name = "FullCycle"
}



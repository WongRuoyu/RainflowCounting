package com.wong
package RainflowCounting.model

case class Level(low:Double,high:Double,value:Double){

  def isAtLowerBound(x:Double) = x == low

  def isAtHigherBound(x:Double) = x == high

  def isInside(x:Double) = x > low && x < high

  def isNotInside(x:Double) = !this.isInside(x)

  def toLevel(reversal: Reversal):Level = reversal match {
    case reversal: Peak if isAtHigherBound(reversal.value) => ???
    case reversal: Valley if isAtLowerBound(reversal.value) => ???
    case _ => this
  }

  def next(next:Level):List[Level] = ???

  override def equals(that: Any): Boolean = ???

}

object Level{
  type Temp = Map[(Double,Double),Double]

  def fromMap(maps:Temp):List[Level] = maps.map{
    case ((low,high),value) => Level(low,high,value)
  }.toList

  def fromList(list:List[Double]):List[Level] = ???
/*  implicit def toLevel(reversal: Reversal):Level = reversal match {
    case reversal:Peak if reversal.value
  }*/
}

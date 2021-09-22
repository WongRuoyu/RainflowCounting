package com.wong
package RainflowCounting.model

case class Level(low:Float,high:Float,value:Float){ self =>

  def isOnLowerBound(r:Reversal) = r.value == low

  def isOnHigherBound(r:Reversal) = r.value == high

  def isInside(r:Reversal) = r.value > low && r.value < high

  def isNotInside(r:Reversal) = !self.isInside(r)


  def containReversal(reversal: Reversal): Boolean = reversal match {
    case reversal: Peak => isInside(reversal) || isOnLowerBound(reversal)
    case reversal: Valley => isInside(reversal) || isOnHigherBound(reversal)
  }

   def asReversal(reversal:Reversal): Reversal = reversal match {
      case Peak(p) => Peak(self.value)
      case Valley(v) => Valley(self.value)
   }

  // a single Level is not able to quaify a Reversal, so this method is impossible.
 // def quatify(reversal:Reversal) : Reversal = if(containReversal(reversal)) self.value else

  // def next(next:Level):List[Level] = ???

  // override def equals(that: Any): Boolean = ???

}

object Level{
  type Temp = Map[(Float,Float),Float]

  def fromMap(maps:Temp):List[Level] = maps.map{
    case ((low,high),value) => Level(low,high,value)
  }.toList

  def fromList(list:List[Float]):List[Level] = ???

/*  implicit def toLevel(reversal: Reversal):Level = reversal match {
    case reversal:Peak if reversal.value
  }*/

}

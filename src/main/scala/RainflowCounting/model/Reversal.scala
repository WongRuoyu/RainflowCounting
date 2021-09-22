package com.wong
package RainflowCounting.model

sealed trait Reversal {self =>
  def value:Double

  def name = "Reversal"

  override def equals(that:Any):Boolean = that match {
    case p:Peak => p.value == self.value
    case v:Valley => v.value == self.value
  }

  override def toString:String = s"%s(%f)".format(name,value)

  def - (other:Reversal):Double = self.value - other.value

  def isOnLowerBound(level:Level): Boolean = self.value == level.low

  def isOnHigherBound(level:Level): Boolean = self.value == level.high
  
  def isOnBoundary(level:Level): Boolean = self.isOnHigherBound(level) || self.isOnLowerBound(level)

  def isInside(level:Level): Boolean = self.value > level.low && self.value < level.high

  def isNotInside(level:Level): Boolean = !self.isInside(level) 
  
  def matchLevel(level:Level): Boolean = self match {
      case p:Peak => self.isInside(level) || self.isOnLowerBound(level) 
      case v:Valley => self.isInside(level) || self.isOnHigherBound(level) 
  }

  // def quantifiedBy(levels:List[Level]):Option[Reversal] = levels.filter(_.containReversal(p)).asReversal(p)

}

object Reversal {self =>


  type Temp = (List[Reversal],Double)

  def reversals(data:List[Double]): List[Reversal] = data match {
      case Nil => Nil
      case x::Nil => Nil
      case a::b::Nil if a > b => List(Valley(b)).+:(Peak(a))
      case a::b::Nil if a == b => Nil
      case a::b::Nil if a < b => List(Peak(b)).+:(Valley(a))
      case _ => {
        val init: Temp = (Nil, data.head)
        val media: List[Reversal] = data.tail.foldLeft(init)(reversal2(_, _))._1
        val last: List[Reversal] = media match {
          case Nil => Nil
          case _ => if (data.last > media.head.value) List(Peak(data.last)) else if (data.last == media.head.value) Nil else List(Valley(data.last))
        }
        val result:List[Reversal] = last.:::(media.reverse)
        result
      }
    }

  private def reversal2(re:Temp,x:Double):Temp = re._1 match {
    case Nil if re._2 > x => (Peak(re._2)::Nil,x)
    case Nil if re._2 < x => (Valley(re._2)::Nil,x)
    case Nil if re._2 == x => (Nil,x)
    case _ if re._1.head.value > re._2 && x > re._2 =>  (re._1.+:(Valley(re._2)).toList,x)
    case _ if re._1.head.value < re._2 && x < re._2 =>  (re._1.+:(Peak(re._2)).toList,x)
    case _ => (re._1,x)
  }
}


case class Peak(value:Double) extends Reversal{
  override def name = "Peak"
}
case class Valley(value:Double) extends Reversal{
  override def name = "Valley"
}



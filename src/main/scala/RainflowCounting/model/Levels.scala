package com.wong
package RainflowCounting.model


object Levels {
  def fromList(levels:List[Float]):List[Level]= levels match {
    case Nil => Nil
    case (x::Nil) => Nil
    case _ => levels.init.zip(levels.tail).map{
                case  (low,high) => Level(low,high,(low+high)*0.5f)
              }
    }
}

trait Levels {

  def quatifyReversals(levels:List[Level])(reversals:List[Reversal]): List[Reversal] = for {
    level <- levels
    reversal <- reversals
    if level.containReversal(reversal)
  } yield level.asReversal(reversal) 

} 

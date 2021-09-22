package com.wong
package RainflowCounting.model

import scala.math.abs


object RainflowCounting {

  def counting_4P(data: List[Reversal]): List[Cycle] = ???

  def counting_TP(data: List[Reversal]): List[Cycle] = data match {
    case Nil => Nil
    case x1 :: Nil => Nil
    case x1 :: x2 :: Nil => HalfCycle(x1, x2) :: Nil
    case _ => counting(Nil)(data.head)(data.tail)
  }

  private def counting(remaining: List[Reversal])(p: Reversal)(xs: List[Reversal]): List[Cycle] = xs match {
    case Nil => halfCycles((remaining.+:(p)).reverse.toList)
    //        start from the head
    case _ if remaining.length == 0 => counting(remaining.+:(p))(xs.head)(xs.tail)
    //        rY > rX
    case _ if abs(remaining.head - p) > abs(p - xs.head) => counting(remaining.+:(p))(xs.head)(xs.tail)
    //        rY <= rX
    case _ if abs(remaining.head - p) <= abs(p - xs.head) => remaining match {
      case Nil => counting(remaining.+:(p))(xs.head)(xs.tail)
      case x :: Nil => (counting(remaining.tail.+:(p))(xs.head)(xs.tail)).+:(HalfCycle(remaining.last, p))
      case a :: b :: Nil => counting(remaining.tail)(xs.head)(xs.tail).+:(FullCycle(remaining.head, p))
      case a :: b :: c :: rest => counting(remaining.tail.tail)(remaining.tail.head)(xs).+:(FullCycle(a, p))
    }
  }

  private def halfCycles(reversals: List[Reversal]):List[Cycle] = reversals.init.zip(reversals.tail).map {
    case (v1, v2) => HalfCycle(v1, v2)
  }

}

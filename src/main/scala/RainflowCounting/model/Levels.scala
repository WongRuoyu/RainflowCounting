package com.wong
package RainflowCounting.model


object Levels {
  def fromList(levels:List[Double]):List[Level]= levels match {
    case x::y::rest => fromList(rest).::(Level(x,y,(x+y)*0.5))
  }
}

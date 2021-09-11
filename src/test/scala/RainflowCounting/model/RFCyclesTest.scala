package com.wong.RainflowCounting.model


import org.specs2.mutable.Specification

object RFCyclesTest extends Specification {
  "CyclesTest" >> {
    "A HalfCycle" >> {
      val halfCycle = HalfCycle(Peak(1.0),Valley(2.0))
      "toString Test" >> {
        val string = halfCycle.toString()
        string must startWith("HalfCycle")
      }
    }
  }
}
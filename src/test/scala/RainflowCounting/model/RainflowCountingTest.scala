package com.wong
package RainflowCounting.model

import org.specs2.mutable.Specification


object RainflowCountingTest extends Specification {
  "RainflowCountingTest" >> {
    val data = List(Valley(4.0),Peak(7),Valley(2),Peak(10),Valley(5),
        Peak(9),Valley(3),Peak(4),Valley(2),Peak(12),
        Valley(5),Peak(11),Valley(1),Peak(4),Valley(3),
        Peak(10),Valley(6),Peak(12),Valley(4),Peak(8),
        Valley(1),Peak(9),Valley(4),Peak(6))
    val peak = Peak(5.0)
    val valley = Valley(2.0)
    "counting for data" >> {
      val cycles:List[Cycle] = RainflowCounting.counting_TP(data)
//      cycles.foreach(println)
      "cycles size must be 17" >> {
        cycles must haveSize(17)
      }
      "cycles must start with" >> {
        cycles.head must_===(HalfCycle(Valley(4.0),Peak(7.0)))
      }
      "cycles must end with (HalfCycle(Valley(4.0),Peak(6.0))" >> {
        cycles.last must_===(HalfCycle(Valley(4.0),Peak(6.0)))
      }
      "cycles must contains HalfCycle(Valley(5.0),Peak(11.0)" >> {
        cycles must contain(HalfCycle(Valley(5.0),Peak(11.0)))
      }
    }
    "An Empty List of reversals should poduce Nil" >> {
      val data = Nil
      val cycles = RainflowCounting.counting_TP(data)
      cycles must_===(Nil)
    }
    "An List of tow elements should produce only a HalfCycle" >> {
      val cycles = RainflowCounting.counting_TP(valley :: peak :: Nil)
      cycles must_=== HalfCycle(valley,peak)::Nil
    }
    "A list of one element should produce a Nil of cycles" >> {
      val cycles = RainflowCounting.counting_TP(peak::Nil)
      cycles must_=== Nil
    }
}
}
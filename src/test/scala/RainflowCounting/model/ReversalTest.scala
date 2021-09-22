package com.wong.RainflowCounting.model


import org.specs2.mutable.Specification

object ReversalTest extends Specification {
  "ReversalTest" >> {
    "PeakTest" >> {
      val peak = Peak(1.0)
      "toString Test" >> {
        val string = peak.toString
        string must startWith("Peak")
      }
      " - method Test" >> {
        val valley1 = Valley(3.0)
        val delta = peak - valley1
        delta must_=== (-2)
      }
    }
    "the reversal method Test" >> {
      "the reversal method should extract the reversals correctly" >> {
        "reversals should be Nil when applied to Nil" >> {
          val data = Nil
          val result = Reversal.reversals(data)
          result must_=== (Nil)
        }
        "2-element List with the same values Test" >> {
          val data = List(1.0, 1.0)
          val reversals = Reversal.reversals(data)
          "should produce Nil" >> {
            reversals must_=== (Nil)
          }
        }
        "many elements List with the same values Test" >> {
          val data = List(1.0, 1.0, 1.0, 1.0, 1.0)
          val reversals = Reversal.reversals(data)
          "should produce Nil" >> {
            reversals must_=== (Nil)
          }
        }
        "A List begins with many elements List with the same values Test" >> {
          val data = List(1.0, 1.0, 1.0, 2.0)
          val reversals = Reversal.reversals(data)
          "reversals must have a length of 2" >> {
            reversals.length must_=== (2)
          }
          "reversals must starts with Valley(1.0)" >> {
            val head_e = Valley(1.0)
            reversals.head must_=== (head_e)
          }
          "reversals must end with Peak(2.0)" >> {
            val peak = Peak(2.0)
            reversals.last must_=== (peak)
          }
        }
        "A List ends with many elements with the same values Test" >> {
          val data = List(2.0, 1.0, 1.0, 1.0)
          val reversals = Reversal.reversals(data)
          "reversals must have a length of 2" >> {
            reversals.length must_=== (2)
          }
          "reversals must ends with Valley(1.0)" >> {
            val head_e = Valley(1.0)
            reversals.last must_=== (head_e)
          }
          "reversals must start with Peak(2.0)" >> {
            val peak = Peak(2.0)
            reversals.head must_=== (peak)
          }
        }
        "A list begins and ends with identical values test" >> {
          val data = List(1.0,1.0,1.0,2.0,2.0,2.0)
          val reversals = Reversal.reversals(data)
          "reversals' length must be 2">>{
            reversals.length must beEqualTo(2)
          }
          "reversals must begin with Valley(1.0)">>{
            val head_e = Valley(1.0)
            reversals.head must_===(head_e)
          }
          "reversals must end with Peak(2.0)">>{
            val last_e = Peak(2.0)
            reversals.last must_===(last_e)
          }
        }
        "A list begins with identical values,then different values and ends with identical values test" >> {
          val data = List(1.0,1.0,1.0,3.0,4.0,5.0,-1.0,2.0,2.0,2.0)
          val reversals = Reversal.reversals(data)
          "reversals' length must be 4">>{
            reversals.length must beEqualTo(4)
          }
          "reversals' head must be Valley(1.0)">>{
            val head_e = Valley(1.0)
            reversals.head must_===(head_e)
          }
          "reversals' last element must be Peak(2.0)">>{
            val last_e = Peak(2.0)
            reversals.last must_===(last_e)
          }
          "reversals' second element must be Peak(5.0)">>{
            val second_e=Peak(5.0)
            reversals.tail.head must_===(second_e)
          }
          "reversals must contain Valley(-1.0)">>{
            val expected = Valley(-1.0)
            reversals must contain(expected)
          }
        }
        "2 elements List with different values Test" >> {
          "ascend order Test" >> {
            val data = List(1.0, 2.0)
            val result_e = List(Valley(1.0), Peak(2.0))
            val result = Reversal.reversals(data)
            result must_=== (result_e)
          }
          "descend order Test" >> {
            val data = List(3.0, 2.0)
            val result_e = List(Valley(3.0), Peak(2.0))
            val result = Reversal.reversals(data)
            result must_=== (result_e)
          }
        }
        "Even number of elements Test" >> {
          val data = List(4.0, 7, 2, 10, 5, 9, 3, 5)
          val reversals = Reversal.reversals(data)
          "The result reversals must not be empty" >> {
            reversals.length mustNotEqual (0)
          }
          "The result reversals must have a length of 8" >> {
            reversals.length must_=== (8)
          }
          "The reversals must start With Valley(4.0)" >> {
            val heading = Valley(4.0)
            reversals.head must_=== (heading)
          }
          "The reversals must end With Peak(5.0)" >> {
            val ending = Peak(5.0)
            reversals.last must_=== (ending)
          }
          "The reversals must contains Peak(10.0)" >> {
            val peak = Peak(10.0)
            reversals must contain(peak)
          }
          "The third element msut be Valley(2.0)" >> {
            val valley = Valley(2.0)
            reversals.tail.tail.head must_=== (valley)
          }
          "The last second element msut be Valley(3.0)" >> {
            val valley = Valley(3.0)
            reversals.init.last must_=== (valley)
          }
        }
        "Odd number of elements Test" >> {
          val data = List(4.0, 7, 2, 10, 5, 9, 3)
          val reversals = Reversal.reversals(data)
          "the length of reversals must be 7" >> {
            reversals.length must_=== (7)
          }
          "the first reversal must be Valley(4.0)" >> {
            val head_e = Valley(4.0)
            reversals.head must_=== (head_e)
          }
          "the last reversal must be Valley(3.0)" >> {
            val ending = Valley(3.0)
            reversals.last must_=== (ending)
          }
          "the second element of reversals must be Peak(7.0)" >> {
            val peak = Peak(7.0)
            reversals.tail.head must_=== (peak)
          }
          "the last second element of reversals must be Peak(9.0)" >> {
            val peak = Peak(9.0)
            reversals.init.last must_=== (peak)
          }
        }
      }
    }
  }
}

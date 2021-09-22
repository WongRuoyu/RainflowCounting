package com.wong
package RainflowCounting.model

import org.specs2.mutable.Specification

object LevelsTest extends Specification with Levels{
  "LevelsTest" >> {
    val list = (0.0F to 11.0F by 2.0F).toList 
    val value = List(Level(0F,2F,1F),Level(2F,4F,3F),Level(4F,6F,5F),Level(6F,8F,7F),Level(8F,10F,9F))
    val levels = Levels.fromList(list)
    "fromList method Test" >> {
      "it should produce a List of Levels from a list of float" >> {
          levels must_=== value
      }
      "it should produce  Nil when applied Nil" >> {
          val levels = Levels.fromList(Nil)
          levels must_=== Nil
      }
    } 
    "quatifyReversals method Test" >> {
      "Nil input Test should produc Nil" >> {
        "levels as Nil" >> {
          val reversals = List(Valley(1.0),Peak(2.0))
          val quatified = quatifyReversals(Nil)(reversals)
          quatified must_=== Nil
        }
        "reversals as Nil" >> {
          val quatified = quatifyReversals(levels)(Nil)
          quatified must_=== Nil
        }
        "both as Nil" >> {
          val quatified = quatifyReversals(Nil)(Nil)
          quatified must_=== Nil
        }
      }
      "this method should quatify reversals correctly" >> {
        "all reversals are inside levels" >> {
          val reversals = List(Valley(1.0),Peak(3.0))
          val quatified = quatifyReversals(levels)(reversals)
          val expected = List(Valley(1.0),Peak(3.0))
          quatified must_=== expected
        }
        "all reversals are  one boundary" >> {
          val reversals = List(Valley(2.0),Peak(2.0))
          val quatified = quatifyReversals(levels)(reversals)
          val expected = List(Valley(1.0),Peak(3.0))
          quatified must_=== expected
        }
        "mixtures reversals" >> {
          val reversals = List(Valley(2.0),Peak(2.0),Valley(3.0),Peak(5.0),Valley(8.0),Peak(6.0))
          val quatified = quatifyReversals(levels)(reversals)
          val expected = List(Valley(1.0),Peak(3.0),Valley(3.0),Peak(5.0),Valley(7.0),Peak(7.0))
          quatified must_=== expected
        }
      }
    }
  }
}

package com.wong
package RainflowCounting.model

import org.specs2.mutable.Specification

object LevelTest extends Specification {
  "LevelTest" >> {
    val level = Level(0f,2f,1f)
    "the isOnLowerBound method Test" >> {
          "for Peaks" >> {
              val peak = Peak(0.0f)
              val result = level.isOnLowerBound(peak)
              result  must beTrue
          }  
          "for Valley" >> {
              val valley = Valley(0.0f)
              val result = level.isOnLowerBound(valley)
              result  must beTrue
          }  
    } 
    "the isOnHigherBound method Test" >> {
          "for Peaks" >> {
              val peak = Peak(2.0f)
              val result = level.isOnHigherBound(peak)
              result  must beTrue
          }  
          "for Valley" >> {
              val valley = Valley(2.0f)
              val result = level.isOnHigherBound(valley)
              result  must beTrue
          }  
    }
    "the isInside method Test" >> {
          "for Peaks" >> {
            "the inside case" >> {
              val peak = Peak(0.5f)
              level.isInside(peak) must beTrue
            }
            "the not inside case" >> {
              val peak = Peak(5.0f)
              level.isInside(peak)  must beFalse
            }
          }  
          "for Valley" >> {
            "the inside case" >> {
              val valley = Valley(0.5f)
              level.isInside(valley)  must beTrue
            }
            "the not inside case" >> {
              val valley = Peak(5.0f)
              level.isInside(valley)  must beFalse
            }
          }  
    }
    "the containReversal method Test" >> {
          "for Peaks" >> {
            "the normal true case" >> {
              val peak = Peak(0.5f)
              level.containReversal(peak) must beTrue
            }
            "the onBoundary true case" >> {
              val peak = Peak(0f)
              level.containReversal(peak) must beTrue
            }
            "the onBoundary false case" >> {
              val peak = Peak(2f)
              level.containReversal(peak) must beFalse
            }
            "the normal false case" >> {
              val peak = Peak(5.0f)
              level.containReversal(peak)  must beFalse
            }
          }  
          "for Valley" >> {
            "the normal true case" >> {
              val valley = Valley(0.5f)
              level.containReversal(valley) must beTrue
            }
            "the onBoundary true case" >> {
              val valley = Valley(0f)
              level.containReversal(valley) must beFalse
            }
            "the onBoundary false case" >> {
              val valley = Valley(2f)
              level.containReversal(valley) must beTrue
            }
            "the normal false case" >> {
              val valley = Valley(5.0f)
              level.containReversal(valley)  must beFalse
            }
          }  
    }
  }
}

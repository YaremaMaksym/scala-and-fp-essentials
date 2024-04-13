package lectures.part3fp

import scala.util.Random

object Sequences extends App{

  // Seq
  val aSequence = Seq(1,5,0,-1,2)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(9,4,2))
  println(aSequence.sorted)

  // Range
  val aRange: Seq[Int] = 1 to 10
  println(aRange) // "Range 1 to 10"
  aRange.foreach(println)

   // List
   val aList = List(1,2,3)
   val prepended = 42 +: aList
   println(prepended)

   val apples5 = List.fill(5)("apple")
   println(apples5)
   println(apples5.mkString("-"))

   // arrays
   val numbers = Array(1,2,3,4)
   val threeElements = Array.ofDim[String](3)
   threeElements.foreach(println)

   // mutation
   numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
   println(numbers.mkString(" "))

   // arrays and seq
   val numbersSeq: Seq[Int] = numbers // implicit conversion
   println(numbersSeq)

   // vectors
   val vector: Vector[Int] = Vector(1,2,3)
   println(vector)

   // vectors vs lists

   val maxRuns = 1000
   val maxCapacity = 1_000_000

   def getWriteTime(collection: Seq[Int]): Double = {
     val r = new Random
     val times = for {
       x <- 1 to maxRuns
     } yield {
       val startTime = System.nanoTime()
       collection.updated(r.nextInt(maxCapacity), r.nextInt())
       System.nanoTime() - startTime
     }

     times.sum * 1.0 / maxRuns
   }

  // keeps reference to tail
  // updating in the middle takes long time
   val numbersList = (1 to maxCapacity).toList

  // depth of the tree is small
  // needs to replace an entire 32-element chunk
   val numbersVector = (1 to maxCapacity).toVector

   println(getWriteTime(numbersList))
   println(getWriteTime(numbersVector))
}

package lectures.part3fp.part4

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"

  println(x)
  println(description)


  /*
    1. Decompose values
   */
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match
    case Person(n, a) if (a < 21) => s"Hi, my name is $n and I can't drink in the us"
    case Person(n, a) => s"Hi, my name is $n and I'm $a years old"
    case _ => "Hello"

  println(greeting )

  /*
   - cases are matched in order
   - what if no cases match? MatchError
   - type of the PM expression? unified type of all the types in all the cases
   - PM works really well with case classes
   */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched Dog of the $someBreed breed")
  }

  // match everything
  val isEven = x match
    case n if n % 2 == 0 => true
    case _ => false
//      overkill

  val isEvenCond = if (x % 2 == 0) true else false // also bad
  val isEvenNormal = x % 2 == 0
}

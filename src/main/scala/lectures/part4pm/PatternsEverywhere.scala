package lectures.part4pm

object PatternsEverywhere extends App {

  /*
    - Big idea #1
   */

  try {
    // code
  } catch {
    case e: RuntimeException =>
    case npe: NullPointerException =>
    case _ =>
  }

//  catches are actually MATCHES

/*
  try {
    // code
  } catch (e) {
    e match
      case e: RuntimeException =>
      case npe: NullPointerException =>
      case _ =>
  }
 */

  /*
    - Big idea #2
   */
  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield 10 * x

  // generators are also based on PATTERN MATCHING
  val tuples = List((1,4), (2, 3))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // case classes, :: operators, ...

  /*
    - Big idea #3
   */

  val tuple = (1,2,3)
  val (a,b,c) = tuple
  println(b) // 2
  // multiple value definitions based on PATTERN MATCHING
  // ALL THE POWER

  val head :: tail = list
  println(head)
  println(tail)

  /*
    - Big idea #4 - NEW
   */

  // Partial functions (based on Pattern matching)
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 =>
    case _ =>
  } // partial function literal

  val mappedList2 = list.map {
    x => x match
      case v if v % 2 == 0 => v + " is even"
      case 1 =>
      case _ =>
  }

  println(mappedList)
}

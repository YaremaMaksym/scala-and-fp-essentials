package lectures.part3fp

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {

  /*
    1 - constants
   */
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }


  /*
    2 - match anything
   */

  // wildcard
  val matchAnything = x match
    case _ =>

  // variable
  val matchAVariable = x match
    case something => s"I've found $something"

  /*
    3 - Tuples
   */
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 2) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match
    case (_, (2, v)) =>

  // PM can be NESTED!

  /*
    4 - case classes - constructor pattern
   */
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  /*
    5 - list patterns
   */
  val aStandardList = List(1,2,3,42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) =>    // extractor (advanced)
    case List(1, _*) =>         // list  of arbitrary length (advanced)
    case 1 :: List(_) =>        // infix pattern
    case List(1,2,3) :+ 42 =>   // infix pattern
  }

  /*
    6 - type specifics
   */
  val unknown: Any = 2
  val matchUnknown = unknown match
    case list: List[Int] =>     // explicit type specifier
    case _ =>

  /*
    7 - name binding
   */
  val matchNameBinding = aList match
    case nonEmptyList @ Cons(_, _) => // name binding => use name later(here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns

  /*
    8 - multi-patterns
   */
  val multiPattern = aList match
    case Empty | Cons(0, _) => // compound pattern (multi-pattern)

  /*
    9 - if guards
   */
  val secondElementSpecial = aList match
    case Cons(_, Cons(specialElement, Empty)) if specialElement % 2 == 0 =>

  // ALL

  /*
    Question.
   */

  val numbers = List(1,2,3)
  val numbersMatch = numbers match
    case listOfStrings: List[String] => "list of strings"
    case listOfNumbers: List[Int] => "list of numbers"
    case _ => ""

  println(numbersMatch) // "list of strings"
  // JVM trick question
}

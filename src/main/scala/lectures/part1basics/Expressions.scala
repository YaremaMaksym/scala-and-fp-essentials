package lectures.part1basics

object Expressions extends App  {

  val x = 1 + 2 //EXPRESSIONS
  println(s"x = ${x}")

  println(s"2 + 3 * 4 = ${2 + 3 * 4}")
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  println(s"1 == x = ${1 == x}")
  // == != > >= < <=

  println(s"!(1 == x) = ${!(1 == x)}")
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ......... side effects

  // Instructions (DO) vs Expressions (VALUE)

//  IF expression
  val aCondition = true
  val aConditionValue = if (aCondition) 5 else 3
  println(s"aCondition = ${aConditionValue}")
  println(s"if (aCondition) 5 else 3 = ${if (aCondition) 5 else 3}")

  var i = 0
  val aWhile = while (i < 10) {
    println(s"i = ${i}")
    i += 1
  }

//  NEVER WRITE THIS AGAIN

// EVERYTHING in Scala is an Expression!

  val aWeirdValue = (aVariable = 3) // Unit === void
  println(s"aWeirdValue = ${aWeirdValue}")

  // side effects: println(), while, reassigning

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }
  println(s"aCodeBlock = ${aCodeBlock}")
}

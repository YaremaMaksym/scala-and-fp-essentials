package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(s"aFunction(\"hello\", 3) = ${aFunction("hello", 3)}")

  def aParameterlessFunction(): Int = 42
  aParameterlessFunction()

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("Hello", 3))

//  WHEN YOU NEED LOOPS, USE RECURSION

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

//
//
//
  def greetingFunction(name: String, age: Int): Unit = {
    println(s"Hi my name is $name and I am $age years old")
  }

  def factorialFunction(n: Int): Int = {
    if (n <= 0) 1
    else n * factorialFunction(n - 1)
  }

  def fibonacciFunction(n: Int): Int = {
    if (n <= 1) 1
    fibonacciFunction(n - 1) + fibonacciFunction(n - 2)
  }

  def isNumberPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }

  println(s"isNumberPrime(31) = ${isNumberPrime(31)}")
  println(s"isNumberPrime(41) = ${isNumberPrime(41)}")
  println(s"isNumberPrime(32*12) = ${isNumberPrime(32*12)}")
}

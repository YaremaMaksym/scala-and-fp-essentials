package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): BigInt =
    if (n <= 1) 1
    else n * factorial(n-1)

  def anotherFactorial(n: Int): BigInt = {
    def factorialHelper(x: BigInt, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator)

    factorialHelper(n, 1)
  }

  println(s"anotherFactorial(5000) = ${anotherFactorial(5000)}")

  // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION

  def concatenateTailRec(aString: String, n: Int, accumulator: String): String = {
      if (n <= 0) accumulator
      else concatenateTailRec(aString, n - 1, aString + accumulator)
  }
  println(s"concatenateTailRec(5000) = ${concatenateTailRec("hello", 5000, "")}")

  def isPrime(n: Int): Boolean = {
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0)

    isPrimeTailrec(n / 2, true)
  }

  println(s"isPrime(2003) = ${isPrime(2003)}")
  println(s"isPrime(2004) = ${isPrime(2004)}")


  def fibonacci(n: Int): BigInt = {
    def fibonacciTailrec(i: Int, last: BigInt, nextToLast: BigInt): BigInt = {
      if (i >= n) last
      else fibonacciTailrec(i + 1, last + nextToLast, last)
    }

    if (n <= 2) 1
    else fibonacciTailrec(2, 1, 1)
  }

  println(s"fibonacci(8) = ${fibonacci(8)}")

}

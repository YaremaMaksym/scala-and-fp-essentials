package lectures.part2oop

object Exceptions extends App {
  val x: String = null
//  println(x.length)


//  val aWeirdValue: String = throw new NullPointerException()

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("asd")
    else 42

  val potentialFail = try {
    getInt(false)
  } catch {
    case e: RuntimeException => println("caught runtime exception")
  } finally {
    /*
    Code that will executed no matter what
    optional
    doesn't influence the return type of this expression
    use only for side effects
     */
    println("finally")
  }

  // 3. how to define your own exception
  class MyException extends Exception
  val exc = new MyException

//  throw exc
//  throw OutOfMemoryError()
//  throw StackOverflowError()

  class IntOverflowException extends RuntimeException
  class IntUnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by zero")

  class PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new IntOverflowException
      else if (x < 0 && y < 0 && result > 0) throw new IntUnderflowException
      return result
    }

    def sub(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new IntOverflowException
      else if (x < 0 && y > 0 && result > 0) throw new IntUnderflowException
      return result
    }
    def mul(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new IntOverflowException
      else if (x < 0 && y > 0 && result > 0) throw new IntUnderflowException
      else if (x > 0 && y < 0 && result > 0) throw new IntUnderflowException
      else if (x < 0 && y < 0 && result < 0) throw new IntUnderflowException
      return result
    }
    def div(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  val calc = new PocketCalculator
  println(calc.div(1, 0))
  println(calc.add(Int.MaxValue, 1))
}

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
    getInt(true)
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
}

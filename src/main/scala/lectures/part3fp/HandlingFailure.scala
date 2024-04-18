package lectures.part3fp

import scala.util.{Failure, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via apply method
  val potentialFialure = Try(unsafeMethod())
  println(potentialFialure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that may throw
  }

  // utilities
  println(potentialFialure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))

  println(fallbackTry)

  // if you design API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException())
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod().orElse(betterBackupMethod())
  
  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))
}

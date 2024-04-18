package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

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

  /*
    Exercise
   */
  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getConnectionSafe(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // using unsafe methods
  Try(Try(HttpService.getConnection(host, port)).map(_.get("url")).foreach(renderHTML))

  // using safe methods
  HttpService.getConnectionSafe(host, port)
    .flatMap(_.getSafe("url"))
    .foreach(renderHTML)

  // for-comprehension version
  for {
    connection <- HttpService.getConnectionSafe(host, port)
    page <- connection.getSafe("url")
  } renderHTML(page)
  
  
  /* Imperative approach (bad)
    try {
      connection = HttpService.getConnection(host, port)
      try {
        val page = connection.get("url")
        renderHTML(page)
      } catch (other exception)
    } catch (exception) {
    
    }
   */
}

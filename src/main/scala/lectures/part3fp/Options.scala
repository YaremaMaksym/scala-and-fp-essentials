package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // unsafe API
  def unsafeMethod(): String = null
  val result = Option(unsafeMethod())

  // chained methods
  def backupMehod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMehod()))

//  Design unsafe APIs
  def betterUnsafeMethod: Option[String] = None
  def betterBackupMethod: Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod.orElse(betterBackupMethod)


  /*
    Functions on Options
   */
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // Unsafe

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.flatMap(x => Option(x * 10)))
  println(myFirstOption.filter(_ > 10))

  /*
    Exercise
   */
  val config: Map[String, String] = Map(
    // fetch from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "13"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    def random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")
  /*
      if (h != null)
        if (p != null)
          return Connection(h, p)

      return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  /*
      if (c != null)
        return c.connect

      return null
   */
  val connectionStatus = connection.map(c => c.connect)
  connectionStatus.foreach(println)


  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Connection(h, p))
      .map(c => c.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
}

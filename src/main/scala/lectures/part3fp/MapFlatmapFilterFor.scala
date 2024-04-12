package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  //map
  println(s"list.map(_ + 1) = ${list.map(_ + 1)}")
  println(s"list.map(_ + is a number) = ${list.map(_ + " is a number")}")

  //filter
  println(s"list.filter(_ % 2 == 0) = ${list.filter(_ % 2 == 0)}")

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(s"list.flatMap(toPair) = ${list.flatMap(toPair)}")

  // "iterations"
  val numbers = List(1,2,3,4)
  val characters = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  val combinations = numbers.filter(_ % 2 == 0).flatMap(n => characters.flatMap(c => colors.map(color => "" + n + c + "-" + color)))
  println(s"combinations = ${combinations}")

  // foreach
  list.foreach(println)

  /*
    For-comprehensions
   */
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- characters
    color <- colors
  } yield ("" + n + c + "-" + color)
  println(s"forCombinations = ${forCombinations}")

  for {
    n <- numbers
  } println(n)


  /*
    Syntax overload
   */
  list.map { x =>
    x * 2
  }

  /*
    1. MyList supports for comprehensions?
    2. A small collection of at most ONE element = Maybe[+T]
        - map, flatMap, filter
   */
  
}

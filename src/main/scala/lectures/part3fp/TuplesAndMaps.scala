package lectures.part3fp

object TuplesAndMaps extends App {

  // Tuples = finite ordered "lists"
  val aTuple = (2, "Hello Scala") // Tuple2[Int, String] = (2, "Hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "Hi Java"))
  println(aTuple.swap)


  // Maps - key -> value
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Mary"))

  val newPhonebook = phonebook + ("Mary" -> 678)
  println(newPhonebook)

  // functions on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)
  // mapValues
  println(phonebook.view.mapValues(number => "+380" + number).toMap)

  // conversations to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) // used often in Spark
}

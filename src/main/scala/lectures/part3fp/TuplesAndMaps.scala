package lectures.part3fp

object TuplesAndMaps extends App {

  // Tuples = finite ordered "lists"
  val aTuple = (2, "Hello Scala") // Tuple2[Int, String] = (2, "Hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "Hi Java"))
  println(aTuple.swap)


  // Maps - key -> value
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789, "JIM" -> 9000).withDefaultValue(-1)
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

  /*
    1. Overly simplified social network based on maps
        Person = String
        - add person to the network
        - remove
        - friend (mutual)
        - unfriend

        - number of friends of a person
        - person with most friends
        - how many people have NO friends
        - if there is social connection between two people (direct or not)
   */

  def add(network: Map[String, Set[String]], newPerson: String): Map[String, Set[String]] =
    network + (newPerson -> Set())

  def addConnection(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def removeConnection(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, removeConnection(networkAcc, person, friends.head))
    }
    val withRemovedConnection = removeAux(network(person), network)
    withRemovedConnection - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Mary"), "Bob")
  println(network)
  println(addConnection(network, "Bob", "Mary"))
  println(removeConnection(addConnection(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(addConnection(network, "Bob", "Mary"), "Bob"))


  val people = add(add(add(empty, "Mary"), "Bob"), "Jim")
  val jimBob = addConnection(people, "Bob", "Jim")
  val testNetwork = addConnection(jimBob, "Bob", "Mary")

  println(testNetwork)

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  println(nFriends(testNetwork, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  println(mostFriends(testNetwork))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.count(pair => pair._2.isEmpty)
  }

  println(nPeopleWithNoFriends(jimBob))

  def isConnected(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(isConnected(testNetwork, "Jim", "Mary"))
  println(isConnected(jimBob, "Jim", "Mary"))
}

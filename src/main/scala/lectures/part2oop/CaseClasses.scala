package lectures.part2oop

object CaseClasses extends App{

  // equals, hashCode, toString

  case class Person(name: String, age: Int)

  // 1. class parameters are promoted to fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. implemented toString
  println(jim) // equal to jim.toString

  // 3. implemented equals and hashCode
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. implemented copy
  val jim3 = jim.copy(age = 45)
  println(jim)

  // 5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  // Akka

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
}

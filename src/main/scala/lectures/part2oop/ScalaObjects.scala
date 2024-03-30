package lectures.part2oop

object ScalaObjects extends App {

//  Scala DOESN'T HAVE CLASS-LEVEL FUNCTIONALITY ("static")

  object Person { //type + its only instance
//    "static" / "class" - functionality
    val N_EYES = 2
    def canFly = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level - functionality
  }
//  COMPANIONS (class and object, because they are in same SCOPE and have same NAME)

  println(Person.N_EYES)
  println(Person.canFly)

//  Scala object = SINGLETON INSTANCE
  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val person1 = Person
  val person2 = Person
  println(person1 == person2)

  val bobbie = Person(mary, john) // Person.apply(mary, john)


  /**
   * Scala Applications = Scala object with
   *  def main(args: Array[String]): Unit
   */
}

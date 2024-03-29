package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  person.greet("Alex")
}

class Person(name: String, val age: Int = 0) { // constructor
  // body
  val x = 2

  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says, Hi $name")

  // overloading
  def greet(): Unit = println(s"Hi, I'm ${this.name}")

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
// class parameters are NOT FIELDS

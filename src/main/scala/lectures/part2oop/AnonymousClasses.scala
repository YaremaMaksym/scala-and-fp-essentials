package lectures.part2oop

object AnonymousClasses extends App {
  trait Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal = new Animal {
    override def eat: Unit = println("ahahahahahahha")
  }
  /*
  equivalent

  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("ahahahahahahha")
  }
  val funnyAnimal: Animal = new AnonymousClasses$$anon$1
 */

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi I'm $name")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, how can I help you?")
  }
}

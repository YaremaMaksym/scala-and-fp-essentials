package lectures.part2oop

object Inheritance extends App {

  sealed class Animal {
    val creacuteType = "wild"
    def eat = println("nomnomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch


  /**
   * constructors
   */
  class Person(name: String, age: Int){
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  /**
   * overriding
   */
  class Dog(override val creacuteType: String) extends Animal {
//    override val creacuteType: String = "domestic"
    override def eat = {
      super.eat
      println("crunch crunch")
    }
  }
  val dog = new Dog("K9")
  dog.eat
  println(dog.creacuteType)

  /**
   * type substitution (broad: polymorphism)
   */
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  /**
   * preventing overrides
   * 1 - use final on member
   * 2 - use final on the entire class
   * 3 - seal the class = extend class in THIS FILE, prevent extension in other files
   */
}

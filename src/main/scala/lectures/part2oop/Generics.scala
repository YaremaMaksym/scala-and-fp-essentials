package lectures.part2oop

object Generics extends App {
  class MyList[+A] {
    // use type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Dog = Animal

     */
  }
  class MyMap[Key, Value]

  val listOfStrings = new MyList[String]
  val listOfIntegers = new MyList[Int]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  /**
   * Variance problem
   */
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
//  animalList.add(new Dog) ??? => we return a list of Animals

  // 2. no = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! = CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]


  /**
   * bound types
   */
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)
}

package lectures.part2oop

import scala.reflect.ClassTag.Null

object AbstractDataTypes extends App {
Null
  // abstract
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal:
    override val creatureType: String = "Canine"
    override def eat: Unit = println("crunch crunch")

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  class Crocodile extends Animal with Carnivore:
    override val creatureType: String = "croc"
    override def eat: Unit = println("nom nom nom")
    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  /**
   * traits vs abstract classes
   *
   * 1 - traits don't have constructor params
   * 2 - multiple traits may be inherited by same class
   * 3 - trait = behavior, abstract class = "thing"
   */
}

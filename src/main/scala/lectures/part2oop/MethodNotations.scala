package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} hangs out with ${person.name}"
    def unary_! : String = s"$name, wht!?"
    def isAlive : Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def +(info: String): Person = new Person(this.name + s" ($info)", this.favoriteMovie)
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def learns(thing: String) = s"$name learns $thing"
    def learnsScala: String = learns("Scala")
    def apply(n: Int) = s"$name watched $favoriteMovie $n times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")
  // infix notation = operator notation (syntactic sugar)

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)

  println(s"1+2 = ${1 + 2}")
  println(s"1.+(2) = ${1.+(2)}")

  // ALL OPERATORS ARE METHODS
  // Akka actors have ! ? methods

  /**
   * prefix notation
   */
  val x = -1
  val y = 1.unary_-
  // unary_ prefix works only with - + ~ !

  println(!mary)
  println(mary.unary_!)

  /**
   * postfix notation
   */
  println(mary.isAlive)
  println(mary isAlive)

  /**
   * apply
   */
  println(mary.apply())
  println(mary())




  println((mary + "the rockstar").name)
  println((+mary).age)
  println(mary learns "thing")
  println(mary learnsScala)
  println(mary(4))


}

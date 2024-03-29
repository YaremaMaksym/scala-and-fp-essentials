package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  person.greet("Alex")

  val author = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(s"novel.getAuthorAgeAtRelease() = ${novel.getAuthorAgeAtRelease()}")
  println(s"novel.isWrittenBy(author) = ${novel.isWrittenBy(author)}")

  val counter = new Counter
  counter.inc.print
  counter.inc(10).print
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

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName(): String = s"$firstName $surname"
}

class Novel(name: String, year: Int, author: Writer) {
  def getAuthorAgeAtRelease(): Int = year - author.year
  def isWrittenBy(author: Writer): Boolean = author.equals(this.author)
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int = 0) {
  def inc: Counter = {
    println("incrementing")
    new Counter(count + 1) // immutability
  }
  def dec: Counter = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n<=0) this
    else inc.inc(n - 1)
  }
  def dec(n: Int): Counter = {
    if (n<=0) this
    else dec.dec(n - 1)
  }

  def print = println(s"count = ${count}")
}

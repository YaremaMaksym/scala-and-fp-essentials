package lectures.part1basics

object CBVvsCBN extends App {

  def calledByValue(x: Long): Unit = {
    println(s"x by value = ${x}")
    println(s"x by value = ${x}")
  }

  def calledByName(x: => Long): Unit = {
    println(s"x by name = ${x}")
    println(s"x by name = ${x}")
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  printFirst(42, infinite())
}

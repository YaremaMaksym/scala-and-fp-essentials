package exercises
/*
abstract class MyList(val head: Node, val tail: Node) {

  head = first element of the list
  tail = remainder of the list
  isEmpty
  add(int) => new list with this el added
  toString => a string representation of the list
  def isEmpty: Boolean =
    if (head == null) true else false
  def add(number: Int): MyList = new MyList(head, ) {}
}

class Node(val number: Int, val nextNode: Node)*/

abstract class MyList {
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList:
  override def head: Int = h
  override def tail: MyList = t
  override def isEmpty: Boolean = false
  override def add(element: Int): MyList = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"


object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(s"list.head = ${list.tail.head}")
  println(s"list.add(5).head = ${list.add(5).head}")
  println(s"list.isEmpty = ${list.isEmpty}")
  println(s"list = ${list}")
}
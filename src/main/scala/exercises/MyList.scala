package exercises

import scala.runtime.Nothing$

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

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A]:
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

object ListTest extends App {
//  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  println(s"list.head = ${list.tail.head}")
//  println(s"list.add(5).head = ${list.add(5).head}")
//  println(s"list.isEmpty = ${list.isEmpty}")
//  println(s"list = ${list}")
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(s"listOfIntegers = ${listOfIntegers}")
  println(s"listOfStrings = ${listOfStrings}")
}
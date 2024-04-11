package exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]

  // higher-order functions - receives or returns other functions as result
  def filter(predicate: A => Boolean): MyList[A]
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(f: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C]
  def fold[B](start: B) (f: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def foreach(f: Nothing => Unit): Unit = ()
  def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("List do not have same length")
    else Empty
  def fold[B](start: B) (f: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(head)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)


  def foreach(f: A => Unit): Unit =
    f(h)
    t.foreach(f)

  def sort(f: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (f(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(f)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("List do not have same length")
    else new Cons(f(h, list.head), t.zipWith(list.tail, f))

  def fold[B](start: B) (f: (B, A) => B): B = {
    val newStart = f(start, h)
    t.fold(newStart)(f)
  }
}

object ListTest extends App {
//  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  println(s"list.head = ${list.tail.head}")
//  println(s"list.add(5).head = ${list.add(5).head}")
//  println(s"list.isEmpty = ${list.isEmpty}")
//  println(s"list = ${list}")
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, Empty))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(s"listOfIntegers = ${listOfIntegers}")
  println(s"listOfStrings = ${listOfStrings}")

  println(listOfIntegers.map(elem => elem * 2))

  println(listOfIntegers.filter(elem => elem % 2 == 0))

  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))))

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(cloneListOfIntegers == listOfIntegers)

  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x, y) => y - x))
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))
}
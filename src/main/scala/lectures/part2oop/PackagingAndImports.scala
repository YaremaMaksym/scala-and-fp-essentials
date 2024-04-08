package lectures.part2oop

import lectures.part2oop.Generics.MyList
import playground.{Cinderella => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
  val list = new MyList

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val cind = new Princess
  val prince = new PrinceCharming

  val date = new Date()
  val sqlDate = new SqlDate(2024, 4, 8) //Aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}

package lectures.part1basics

object DefaultArgs extends App {

  def factorialTailrec(n: Int, acc: Int = 1): Int =
    if (n<=1) acc
    else factorialTailrec(n-1, n * acc)

  val fact10 = factorialTailrec(10, 2)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving pic")
  savePicture(width = 800)
  savePicture(width = 200, height = 100, format = "png")

}

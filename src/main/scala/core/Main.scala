package core

class Main {

}

object Main {
  def main(args: Array[String]): Unit = {

    println("hello");

    var array: Array[Array[String]] = null
    array = Array(Array("ABC", "123"), Array("DEF", "456"))

    val result=array.flatMap(_.toList)

    println("hello")
  }
}

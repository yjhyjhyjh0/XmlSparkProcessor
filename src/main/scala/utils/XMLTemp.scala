package utils

class XMLTemp {

}

object XMLTemp {

  // goal XML.child, next elem experiment
  // main work flow
  def main(args: Array[String]): Unit = {
    println("hello xml")
    //    val elem = <greet>Hello</greet>
    //    println(elem.toString())

    val elem = <layer0>
      <layer1>here
        <layer2>haha</layer2> <layer2>haha2</layer2>
        Hello</layer1>
    </layer0>

    //    println(elem.toString())

    val ch = elem.child

    //    println("elem(0).toString() : " + elem(0).toString())
    //    ch.foreach(child => println(child.text))
    //    println("hello")
    println("elem.label : " + elem.label)


    // look for child elem
    val allChildElem = elem \ "layer1"
    // look for all child elem recursively
    val allChildElemDeep = elem \\ "layer1"

    println("")

    //    val myElem = XML.load("input/input.xml")
    //    println(myElem.toString())
    //
    //    val myChild = myElem.child
    //    println(myChild.toString())
  }
}

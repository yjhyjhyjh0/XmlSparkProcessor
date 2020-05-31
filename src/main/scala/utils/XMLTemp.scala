package utils

import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

import scala.collection.mutable.ArrayBuffer
import scala.xml.XML

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
        <layer2>haha</layer2> <layer3>test</layer3> <layer2>haha2</layer2>
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
    // look for all child elem recursively Ex:<layer2>haha</layer2><layer2>haha2</layer2>
    val allChildElemDeep = elem \\ "layer2"

    val input = XML.load("input/input.xml")
    val tt = (input \\ "layer31").head.text.trim
    //    <layer31>
    //      <value>value1</value>
    //    </layer31>
    //    v11.head.text = value1

    //    val v11 = tt \ "value"
    val tt2 = input \\ "object"
    //    <object>
    //      <valueOfField1>value1</valueOfField1>
    //      <valueOfField2>value2</valueOfField2>
    //    </object>


    //    <layer31>
    //      <value>value1</value>
    //    </layer31>
    //      <layer32>
    //        <object>
    //          <valueOfField1>value1</valueOfField1>
    //          <valueOfField2>value2</valueOfField2>
    //        </object>
    //      </layer32>


    //    val re = list.flatMap(l => test(l))
//    val re = list.flatMap { l => test(l) }
//    val re = list.map { l => test(l) }

//    List(1,4,9).flatMap { x =>
//      List(x,x+1) }

//    val re=Array(Array("col1"),Array("col2"),Array("col3")).flatMap{array=>
//      test(array)
//    }
//    println("")

//    val arr2 = Array("What's", "up", "doc?")
//    echo(arr2: _*)
//
    val list = List("here","there")

//    list.flatMap()

  }

  def echo(args: String*): Unit ={
    println(args)
  }


  def test(array: Array[String]): Seq[Row] = {
    Seq(Row(array(0), array(1), array(2)))
  }
}

package core

//import org.scalatest.FunSuite

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.scalatest.funsuite.AnyFunSuite
import utils.SchemaHandler

import scala.xml.XML

class XmlTransformerTest extends AnyFunSuite {

  test("transform") {
    val session = SparkSession
      .builder()
      .appName("Name")
      .master("local")
      .config("spark.executor.heartbeatInterval", "10000000")
      .config("spark.network.timeout", "10000001")
      .getOrCreate()

    val xmlRdd = session
      .sparkContext
      .wholeTextFiles("input")
      .map { case (fileName, fileContent) =>
        println("fileName :" + fileName)
        (fileName, XML.loadString(fileContent))
      }

    val context = SchemaHandler.getFakeTransformerContext()
    val re = XmlTransformer.transform(context, xmlRdd)

    val nestedObjectSchema = StructType(List(StructField("pk", StringType, true),
      StructField("name", StringType, true),
      StructField("myObjects",
        StructType(List(
          StructField("field1", StringType, true),
          StructField("field2", StringType, true))), true)))

    //        val nestedObjectSchema = StructType(List(StructField("pk", StringType, true),
    //          StructField("name", StringType, true)))
    //    session.createDataFrame()

    val df = session.createDataFrame(re, nestedObjectSchema)
    df.printSchema()
    df.show(false)
    println("here")
  }
}

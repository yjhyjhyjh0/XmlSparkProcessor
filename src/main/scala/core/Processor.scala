package core

import org.apache.spark.sql.SparkSession
import utils.{Env, Logging}

import scala.xml.XML

class Processor extends Logging {

  def process(): Unit = {

    val session = SparkSession
      .builder()
      .appName("Name")
      .getOrCreate()

    val xmlRdd = session
      .sparkContext
      .wholeTextFiles("input")
      .map { case (fileName, fileContent) =>
        println("fileName :" + fileName)
        (fileName, XML.loadString(fileContent))
      }

    val mappingConfig = Env.getFakeSchema()
    XmlTransformer.transform(mappingConfig, xmlRdd)


  }
}

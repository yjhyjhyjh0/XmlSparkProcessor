package core

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import utils.{Env, Logging, SchemaHandler}

import scala.xml.XML

class Processor extends Logging {

  def process(): Unit = {
    val pipelineConfig = SchemaHandler.getFakePipelineConfig()
    val session = SparkSession
      .builder()
      .appName("Name")
      .master("local")
      .config("spark.executor.heartbeatInterval", "10000000")
      .config("spark.network.timeout", "10000001")
      .getOrCreate()

    val xmlRdd = session
      .sparkContext
      .wholeTextFiles(pipelineConfig.inputPath)
      .map { case (fileName, fileContent) =>
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

    val df = session.createDataFrame(re, nestedObjectSchema)
    df.printSchema()
    df.show(false)
  }
}

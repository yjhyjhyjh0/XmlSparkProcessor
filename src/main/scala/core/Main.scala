package core

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object Main {
  def main(args: Array[String]): Unit = {
    val processor = new Processor
    processor.process()
  }
}

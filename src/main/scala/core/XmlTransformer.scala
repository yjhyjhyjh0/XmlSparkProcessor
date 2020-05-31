package core

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import utils.{Env, ParseUtils}

import scala.collection.mutable.ArrayBuffer
import scala.xml.Elem

/**
 * Transform XML into RDD of Row
 */
object XmlTransformer {

  /**
   * Extract data from XML based on config
   *
   * @return Seq of Rows, represent rows in output table
   */
  def extractRow(transformContext: TransformContext, xmlElem: Elem): Seq[Row] = {
    val outputSchema = transformContext.mappingConfig.outputSchema.schema
    val columnMapping = transformContext.mappingConfig.mapping.columnMapping

    val mappingConfig = transformContext.mappingConfig
    val subFieldConfig = transformContext.subFieldConfig
    val xmlPathMark = transformContext.xmlPathMark

    // Search XML via Xpath query
    val rowData = outputSchema.collect { case (columnName, columnType) =>
      var columnResult = new ArrayBuffer[String]
      if (columnType == Env.COLUMN_TYPE_UUID) {
        columnResult += ParseUtils.getRandomUUID()
      } else if (columnType == Env.COLUMN_TYPE_STRING) {
        val fullXmlPath = columnMapping(columnName)
        val xmlTag = ParseUtils.extractLastXmlTag(fullXmlPath) // note path is full path
        columnResult += (xmlElem \\ xmlTag)
          .head
          .text
          .trim
      } else if (columnType == Env.COLUMN_TYPE_ARRAY_OF_OBJECT) {
        val subFieldMap = subFieldConfig(columnName)
        subFieldMap.collect { case (subField, subFieldConfig) =>
          columnResult += (xmlElem \\ subFieldConfig).head.text.trim
        }
      } else {
        throw new RuntimeException(s"Not recognized column type :$columnType for column $columnName")
      }
      columnResult.toArray
    }.toSeq

    // Wrap output into Seq[Row]
    Seq(
      Row(
        rowData.map {
          arrayOfData =>
            if (arrayOfData.length == 1)
              arrayOfData.head
            else {
              val r = Row.fromSeq(arrayOfData)
              r
            }
        }: _*) // Note this type ascription for var*
    )
  }

  /**
   * Transform RDD of XML to RDD of rows based on config
   */
  def transform(transformContext: TransformContext, xmlRdd: RDD[(String, Elem)]): RDD[Row] = {
    val rdd = xmlRdd.flatMap { case (_, xmlElem) =>
      extractRow(transformContext, xmlElem)
    }
    rdd
  }
}

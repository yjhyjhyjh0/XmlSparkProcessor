package core

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row

import scala.xml.Elem

/**
 * Transform Xml into RDD of Row
 */
object XmlTransformer {

  def transform(mappingConfig: MappingConfig, xmlRdd: RDD[String, Elem]): (String, RDD[Row]) = {


  }
}

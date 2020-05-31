package utils

import java.util.UUID.randomUUID

/**
 * Utility class for string parsing
 */
object ParseUtils {

  /**
   * Extract last XML tag from full XML path
   *
   * @param fullXmlPath Ex: layer1.layer2.layer31
   */
  def extractLastXmlTag(fullXmlPath: String): String = {
    fullXmlPath.split("\\.").last
  }

  /**
   * Parse single object type column definition into usable structure.
   *
   * @param column           column name
   * @param columnDefinition column definition
   * @return map of [column, map of [field, config] ]
   */
  def parseSubColumn(column: String, columnDefinition: String): Map[String, Map[String, String]] = {

    val subColumnMap = columnDefinition
      .split("\\.")
      .last
      .split("'")
      .drop(1)
      .head
      .split(",")
      .map { singleSubColumn =>
        val array = singleSubColumn.split(":")
        (array.head, array.last)
      }.toMap

    Map(column -> subColumnMap)
  }

  /**
   * Parse object type config base
   *
   * @param objectFieldDefinition object column base
   */
  def parseObjectFieldBase(objectFieldDefinition: String): String = {
    val objectFieldBase = objectFieldDefinition
      .split("\\.")
      .dropRight(1)
      .mkString(".")

    objectFieldBase
  }

  def getRandomUUID(): String = {
    randomUUID.toString
  }
}

package utils

import core.{Mapping, MappingConfig, OutputSchema, OutputTable}

/**
 * Env class for storing environment variables
 */
object Env {

  val COLUMN_TYPE_UUID = "UUID"
  val COLUMN_TYPE_STRING = "S"
  val COLUMN_TYPE_ARRAY_OF_OBJECT = "AOB"

  /**
   * Test purpose for generating fake mapping definition
   */
  def getFakeSchema(): MappingConfig = {
    val table = OutputTable("simpleTable", Array("col1,col2"))
    val schema = OutputSchema(Map(
      "pk" -> COLUMN_TYPE_UUID,
      "col1" -> COLUMN_TYPE_STRING,
      "col2" -> COLUMN_TYPE_ARRAY_OF_OBJECT))
    val mapping = Mapping(Map(
      "col1" -> "layer1.layer2.layer31",
      "col2" -> "layer1.layer2.layer32.object.'col1:field1,'col2:field2'"))
    MappingConfig(table, schema, mapping)
  }
}

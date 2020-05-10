package core

class Config {

}

case class MappingConfig(outputTable: OutputTable, outputSchema: OutputSchema, mapping: Mapping)

/**
 * Output table schema.
 */
case class OutputTable(tableName: String, columnNames: Array[String])

/**
 * Definition of column type.
 *
 * @param schema (columnName,columnTypeConstant)
 */
case class OutputSchema(schema: Map[String, String])

/**
 * Definition of mapping of transformation.
 *
 * @param columnMapping (columnName,mapping config)
 */
case class Mapping(columnMapping: Map[String, String])
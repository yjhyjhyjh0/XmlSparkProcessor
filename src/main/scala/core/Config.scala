package core

import core.Config.{SubColumnConfig, XmlPathMark}

object Config {
  // Parsed config for recording user defined object field
  // Ex: col1 -> (field1->value, ..)
  type SubColumnConfig = Map[String, Map[String, String]]
  // Use contains for checking whether current xml path needs to extract
  // Ex: layer1.layer2 -> col1
  type XmlPathMark = Map[String, String]
}

/**
 * Config of pipeline behavior
 * @param inputPath path of input files
 * @param outputPath path of output files
 */
case class PipelineConfig(inputPath: String, outputPath: String)


/**
 *  Internal data object for recording parsed configs
 */
case class TransformContext(mappingConfig: MappingConfig, subFieldConfig: SubColumnConfig, xmlPathMark: XmlPathMark)

/**
 * Data object for input config
 */
case class MappingConfig(outputTable: OutputTable, outputSchema: OutputSchema, mapping: Mapping)

/**
 * Output table schema.
 */
case class OutputTable(tableName: String, columnNames: Array[String], base: String = "")

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
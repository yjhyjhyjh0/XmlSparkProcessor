package utils

import core.Config.{SubColumnConfig, XmlPathMark}
import core.{Mapping, MappingConfig, OutputSchema, OutputTable, PipelineConfig, TransformContext}

object SchemaHandler {

  /**
   * Create internal usage transform context for Xml transform to parquet
   */
  def createTransformContext(mappingConfig: MappingConfig): TransformContext = {
    val subColumnConfig = createSubColumnConfig(mappingConfig)
    TransformContext(mappingConfig, subColumnConfig, createXmlPathMark(mappingConfig, subColumnConfig))
  }

  /**
   * Create sub column config via input mapping config
   *
   * @return map of [column name, map of [field,config] ]
   */
  def createSubColumnConfig(mappingConfig: MappingConfig): SubColumnConfig = {
    val schema = mappingConfig.outputSchema.schema

    val mapping = mappingConfig.mapping.columnMapping

    val objectColumns = schema.filter(_._2 == Env.COLUMN_TYPE_ARRAY_OF_OBJECT)

    val subColumnConfig = mapping.filter { case (k, _) =>
      objectColumns.contains(k)
    }.map { case (k, v) =>
      ParseUtils.parseSubColumn(k, v)
    }.toArray
      .flatten
      .toMap
    //TODO blog save this flatten to map

    subColumnConfig
  }

  /**
   * Create mark at xml layer for layer visit usage.
   *
   * @return xml path mark in Map to indicate where we need to extract value. Ex: (layer1.layer2.layer31,col1)
   */
  def createXmlPathMark(mappingConfig: MappingConfig, subColumnConfig: SubColumnConfig): XmlPathMark = {
    val schema = mappingConfig.outputSchema.schema
    val mapping = mappingConfig.mapping.columnMapping
    val stringColumns = schema.filter(_._2 == Env.COLUMN_TYPE_STRING)

    // Mark column config at xml path
    val stringColumnPathMark = mapping.filter { case (k, _) =>
      stringColumns.contains(k)
    }.map { case (k, v) =>
      (v, k)
    }

    val objectColumns = schema.filter(_._2 == Env.COLUMN_TYPE_ARRAY_OF_OBJECT)
    val objectFieldPathMark = mapping.filter { case (k, _) =>
      objectColumns.contains(k)
    }.map { case (k, v) =>
      (ParseUtils.parseObjectFieldBase(v), k)
    }

    stringColumnPathMark ++ objectFieldPathMark
  }

  /**
   * Test purpose for generating fake mapping definition.
   */
  def getFakeMappingConfig(): MappingConfig = {
    val table = OutputTable("simpleTable", Array("col1,col2"), "layer1.layer2.layer32.object")
    val schema = OutputSchema(Map(
      "pk" -> Env.COLUMN_TYPE_UUID,
      "col1" -> Env.COLUMN_TYPE_STRING,
      "col2" -> Env.COLUMN_TYPE_ARRAY_OF_OBJECT
    ))
    val mapping = Mapping(Map(
      "col1" -> "layer1.layer2.layer31",
      "col2" -> "layer1.layer2.layer32.object.'field1:valueOfField1,field2:valueOfField2'"
    ))
    MappingConfig(table, schema, mapping)
  }

  /**
   * Test purpose for generating fake transformer context.
   */
  def getFakeTransformerContext(): TransformContext = {
    val mappingConfig = SchemaHandler.getFakeMappingConfig()
    SchemaHandler.createTransformContext(mappingConfig)
  }

  /**
   * Test purpose for generating fake pipeline config.
   */
  def getFakePipelineConfig(): PipelineConfig = {
    PipelineConfig("input", "output")
  }
}

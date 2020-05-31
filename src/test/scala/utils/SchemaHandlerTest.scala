package utils

import org.scalatest.funsuite.AnyFunSuite

class SchemaHandlerTest extends AnyFunSuite {

  test("createSubColumnConfig") {
    val mappingConfig = SchemaHandler.getFakeMappingConfig()
    val subColumnConfig = SchemaHandler.createSubColumnConfig(mappingConfig)

    assert(subColumnConfig("col2")("field1") == "valueOfField1")
    assert(subColumnConfig("col2")("field2") == "valueOfField2")
  }

  test("createXmlPathMark") {
    val mappingConfig = SchemaHandler.getFakeMappingConfig()
    val subColumnConfig = SchemaHandler.createSubColumnConfig(mappingConfig)

    val xmlPathMark = SchemaHandler.createXmlPathMark(mappingConfig, subColumnConfig)

    assert(xmlPathMark.keys.take(1).head == "layer1.layer2.layer31")
    assert(xmlPathMark.values.take(1).head == "col1")

    assert(xmlPathMark.keys.takeRight(1).head == "layer1.layer2.layer32.object")
    assert(xmlPathMark.values.takeRight(1).head == "col2")
  }

  test("createTransformConfig should create parsed internal context for later usage") {
    val mappingConfig = SchemaHandler.getFakeMappingConfig()
    val transformContext = SchemaHandler.createTransformContext(mappingConfig)

    assert(transformContext.subFieldConfig.nonEmpty)
    assert(transformContext.xmlPathMark.nonEmpty)
  }

  test("getFakePipelineConfig") {
    val pipelineConfig = SchemaHandler.getFakePipelineConfig()

    assert(pipelineConfig.inputPath == "input")
    assert(pipelineConfig.outputPath == "output")
  }
}

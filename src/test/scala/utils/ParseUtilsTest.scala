package utils

import org.scalatest.funsuite.AnyFunSuite

class ParseUtilsTest extends AnyFunSuite {

  test("parseSubColumn") {
    val map = ParseUtils.parseSubColumn("col2", "layer1.layer2.layer32.object.'field1:value1,field2:value2'")

    assert(map.keySet.head == "col2")
    assert(map.values.head.head == ("field1", "value1"))
    assert(map.values.head.last == ("field2", "value2"))
  }

  test("parseObjectFieldBase") {
    val base = ParseUtils.parseObjectFieldBase("layer1.layer2.layer32.object.'field1:value1,field2:value2'")
    assert(base == "layer1.layer2.layer32.object")
  }
}

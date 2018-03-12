package uk.kukino.round18.console.controller

import org.scalatest._


class InsertIntoKafkaTest extends FlatSpec {

  var underTest = new InsertIntoKafka()

  it should "not succeed when negative amount" in {
    assert(!underTest.handleInsert(-100).success)
  }

  it should "not succeed when zero amount" in {
    assert(!underTest.handleInsert(0).success)
  }

  it should "not suceed with big quantities" in {
    assert(!underTest.handleInsert(10001).success)
  }

  it should "succeed on quantity within range" in {
    assert(underTest.handleInsert(100).success)
    assert(underTest.handleInsert(200).success)
  }

  it should "include the quantity in the result" in {
    assert(underTest.handleInsert(123).inserted == 123)
  }

  it should "accuse zero inserts when invalid/failed value" in {
    val r = underTest.handleInsert(-1)
    assert(!r.success && r.inserted == 0)
  }

}

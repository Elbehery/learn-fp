package MyClasses

import MyClasses.EqOps._
import MyClasses.eqInstances._
import org.scalatest.{FlatSpec, Matchers}

class EqTest extends FlatSpec with Matchers {

  "Eq" should "eq ints" in {
    1 ==== 1 shouldBe true
    1 ==== 2 shouldBe false
  }

  "Eq" should "eq strings" in {
    "1" ==== "1" shouldBe true
    "1" ==== "2" shouldBe false
  }

  "Eq" should "eq Lists of Ints" in {
    List(1, 2, 3) ==== List(1, 2, 3) shouldBe true
    List(1, 2, 3) ==== List(3, 4, 5) shouldBe false
    List(1, 2) ==== List(1, 2, 3) shouldBe false
    List(1, 2, 3) ==== List(1, 2) shouldBe false
  }

  "Eq" should "eq Lists of stringss" in {
    List("aa", "bbb", "ccc") ==== List("aa", "bbb", "ccc") shouldBe true
    List("aa", "bbb", "ccc") ==== List("a", "bb", "cc") shouldBe false
  }

  "Eq" should "compares different types" in {
    "asd" == 5 shouldBe false
    List(1, 2, 3) == 5 shouldBe false
  }

  "Eq ==== " should "not compare different types" in {
    """"asd" ==== 5 shouldBe false""" shouldNot typeCheck
    """List(1, 2, 3) ==== List("a", "b", "c")""" shouldNot typeCheck
  }

}

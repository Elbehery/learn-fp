package MyClasses

import org.scalatest.{FlatSpec, Matchers}

class TotalOrderTest extends FlatSpec with Matchers {

  import TotalOrderInstances._

  "total order" should "compare ints" in {
    Comparator.less(3, 5) shouldBe true
    Comparator.less(5, 3) shouldBe false
  }

  "total order" should "compare strings" in {
    Comparator.less("3", "5") shouldBe true
    Comparator.less("5", "3") shouldBe false
  }

  "total order" should "compare lists of ints" in {
    Comparator.less(List(5, 10), List(10, 20)) shouldBe true
  }
}

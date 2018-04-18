package MyClasses

import org.scalatest.{FlatSpec, Matchers}

class ShowTest extends FlatSpec with Matchers {

  import MyClasses.showInstances._

  "show" should "show int" in {
    Printer.show(1) shouldBe "1"
  }

  "show" should "show strings" in {
    Printer.show(1.0) shouldBe "1.0"
  }

  "show" should "show List of Ints" in {
    Printer.show(List(1,2,3,4)) shouldBe "[1, 2, 3, 4]"
  }

}

package MyClasses

trait Show[A] {
  def show(x: A): String
}

object Printer {
  def show[A](x: A)(implicit xShow: Show[A]): String = {
    xShow.show(x)
  }
}

object showInstances {
  implicit val intShowInstance: Show[Int] = new Show[Int] {
    override def show(x: Int): String = x.toString
  }

  implicit val doubleShowInstance: Show[Double] = new Show[Double] {
    override def show(x: Double): String = x.toString
  }

  implicit def listShowInstance[T](implicit xShow: Show[T]): Show[List[T]] = new Show[List[T]] {
    override def show(x: List[T]): String = x.map(xShow.show(_)).mkString("[", ", ", "]")
  }
}


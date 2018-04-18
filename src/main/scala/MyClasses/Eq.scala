package MyClasses

trait Eq[A] {
  def eq(lhs: A, rhs: A): Boolean
}

object Eq {
  def eq[T](lhs: T, rhs: T)(implicit eqt: Eq[T]) = eqt.eq(lhs, rhs)
}

class EqOps[T](lhs: T)(implicit eqt: Eq[T]) {
  def ====(rhs: T): Boolean = eqt.eq(lhs, rhs)
}

object EqOps {
  implicit def toEqOps[T](lhs: T)(implicit eqt: Eq[T]) = new EqOps(lhs)
}

object eqInstances {

  implicit val intEqInstance: Eq[Int] = new Eq[Int] {
    override def eq(lhs: Int, rhs: Int): Boolean = lhs == rhs
  }

  implicit val stringEqInstance: Eq[String] = new Eq[String] {
    override def eq(lhs: String, rhs: String): Boolean = lhs == rhs
  }

  implicit def listEqInstance[T](implicit eqt: Eq[T]) = new Eq[List[T]] {
    override def eq(lhs: List[T], rhs: List[T]) = lhs.size == rhs.size && lhs.zip(rhs).forall({ case (l, r) => eqt.eq(l, r) })
  }

}

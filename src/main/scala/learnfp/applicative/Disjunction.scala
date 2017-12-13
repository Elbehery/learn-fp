package learnfp.applicative

import learnfp.functor.Disjunction._
import learnfp.functor.DisjunctionInstance._

object DisjunctionInstance {
  implicit def disjunctionInstance[L, A, R] = new Applicative[A, R, ({type E[X] = Disjunction[L, X]})#E]() {
    override def pure[A](a: A): Disjunction[L, A] = RightDisjunction[L, A](a)
    override def <*>(dfx: Disjunction[L, A => R])(da: Disjunction[L, A]): Disjunction[L, R] = dfx match {
      case LeftDisjunction(lv) => LeftDisjunction[L, R](lv)
      case RightDisjunction(fx) => da fmap fx
    }
  }

  implicit def disjunctionToApplicativeOps[L, A, R](fx:Disjunction[L, A => R])(
    implicit applicative:Applicative[A, R, ({type E[X] = Disjunction[L, X]})#E]) =
    new ApplicativeOps[A, R, ({type E[X] = Disjunction[L, X]})#E](fx)
}